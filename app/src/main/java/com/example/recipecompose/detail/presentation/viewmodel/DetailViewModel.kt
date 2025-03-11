package com.example.recipecompose.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.detail.data.dto.toDetailUiData
import com.example.recipecompose.detail.data.repository.DetailRepository
import com.example.recipecompose.detail.presentation.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadRecipeDetail(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchRecipeDetail(id = id)
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            isError = true,
                            errorMessage = result.errorMessage
                        )
                    }
                }

                is Result.Success -> {
                    val detailUiData = result.data.toDetailUiData()
                    _uiState.update { it.copy(isLoading = false, detailDto = detailUiData) }
                }
            }
        }
    }
}