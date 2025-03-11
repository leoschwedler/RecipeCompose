package com.example.recipecompose.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.home.data.remote.dto.toHomeUiData
import com.example.recipecompose.home.data.remote.repository.HomeRepository
import com.example.recipecompose.home.presentation.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchRecipeRandom()
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.errorMessage
                        )
                    }
                }

                is Result.Success -> {
                    val homeUiData = result.data.map { it.toHomeUiData() }
                    _uiState.update { it.copy(isLoading = false, listRecipeRandom = homeUiData) }
                }
            }
        }
    }
}
