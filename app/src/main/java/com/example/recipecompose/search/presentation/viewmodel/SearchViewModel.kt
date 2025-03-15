package com.example.recipecompose.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.search.data.model.toSearchUiData
import com.example.recipecompose.search.data.repository.SearchRepository
import com.example.recipecompose.search.presentation.model.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {

    val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun loadSearch(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchSearch(query)
            result.fold(
                onSuccess = {
                    val searchUiData = it.map { it.toSearchUiData() }
                    _uiState.update { it.copy(listSearch = searchUiData, isLoading = false) }
                },
                onFailure = {
                    _uiState.update { it.copy(isError = true, isLoading = false) }
                }
            )
        }
    }
}