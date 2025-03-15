package com.example.recipecompose.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.home.data.model.toHomeUiData
import com.example.recipecompose.home.data.repository.HomeRepository
import com.example.recipecompose.home.presentation.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        loadRecipeRandom()
    }

    fun onChangeQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }


    fun loadRecipeRandom() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.fetchRecipeRandom()
            result.fold(onSuccess = {
                val homeUiData = it.map { it.toHomeUiData() }
                _uiState.update { it.copy(isLoading = false, listRecipeRandom = homeUiData) }
            }, onFailure = {
                Log.e("HomeViewModel", "Error: $it")
                if (it is UnknownHostException) {
                    _uiState.update {
                        it.copy(
                            isError = true, errorMessage = "Not internet connection"
                        )
                    }
                } else {
                    _uiState.update { it.copy(isError = true) }
                }
            })
        }
    }
}
