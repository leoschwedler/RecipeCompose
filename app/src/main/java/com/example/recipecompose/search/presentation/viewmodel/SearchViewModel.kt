package com.example.recipecompose.search.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecompose.search.data.api.SearchService
import com.example.recipecompose.search.presentation.model.UiStateSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val service: SearchService): ViewModel() {

    val _uiState = MutableStateFlow(UiStateSearch())
    val uiState = _uiState.asStateFlow()

    fun fetchSearch(query: String){
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val response = service.getSearchQuery(query)
                if (response.isSuccessful){
                    response.body()?.results?.let {result ->
                        _uiState.update { it.copy(listSearch = result, isLoading = false) }
                    }
                }else{
                    Log.e("SearchScreen", "Error code: ${response.code()} - Error body: ${response.errorBody()}")
                }
            }catch (e: Exception){
                Log.e("SearchScreen", "Exception: $e")
            }

        }
    }
}