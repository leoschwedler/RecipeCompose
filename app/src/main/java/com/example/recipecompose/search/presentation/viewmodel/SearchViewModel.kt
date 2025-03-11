package com.example.recipecompose.search.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.search.data.api.SearchService
import com.example.recipecompose.search.data.dto.toSearchUiData
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

    fun loadSearch(query: String){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result =  repository.fetchSearch(query)
            when(result){
                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, isError = true, errorMessage = result.errorMessage) }
                }
                is Result.Success -> {
                    val searchUiData = result.data.map { it.toSearchUiData() }
                    _uiState.update { it.copy(listSearch = searchUiData, isLoading = false) }
                }
            }
        }
    }
}