package com.example.recipecompose.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.home.data.remote.api.HomeService
import com.example.recipecompose.home.presentation.model.UiStateHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(val service: HomeService) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHome())
    val uiState = _uiState.asStateFlow()


    init {
        fetchRecipeRandom()
    }

    fun onChangeQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun fetchRecipeRandom() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val response = service.getRecipeRandom()
                if (response.isSuccessful) {
                    response.body()?.recipes?.let { result ->
                        _uiState.update {
                            it.copy(
                                listRecipeRandom = result
                            )
                        }
                    }
                } else {
                    Log.e(
                        "HomeScreen",
                        "Error code: ${response.code()} - Error Body: ${response.errorBody()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("HomeScreen", "Exception $e")
            }
        }
    }
}
