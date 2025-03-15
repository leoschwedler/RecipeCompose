package com.example.recipecompose.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.detail.data.model.toDetailUiData
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
            result.fold(
                onSuccess = {
                    val detailUiData = it.toDetailUiData()
                    _uiState.update { it.copy(detailDto = detailUiData, isLoading = false) }
                },
                onFailure = {
                    _uiState.update { it.copy(isError = true, isLoading = false) }
                }
            )
        }
    }
}