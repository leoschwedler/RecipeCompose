package com.example.recipecompose.detail.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.detail.data.api.DetailService
import com.example.recipecompose.detail.presentation.model.UiStateDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val service: DetailService): ViewModel() {

    private val _uiState = MutableStateFlow(UiStateDetail())
    val uiState = _uiState.asStateFlow()

     fun fetchRecipeDetail(id: Int){
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val response = service.getRecipeDetail(id)
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        _uiState.update { it.copy(detailDto = result) }
                    }
                } else {
                    Log.e(
                        "DetailScreen",
                        "Error code: ${response.code()} - Error Body: ${response.errorBody()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("DetailScreen", "Exception $e")
            }
        }
    }
}