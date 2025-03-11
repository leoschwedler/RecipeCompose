package com.example.recipecompose.search.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipecompose.commom.components.CustomCardSearch
import com.example.recipecompose.search.presentation.model.SearchUiData
import com.example.recipecompose.search.presentation.model.SearchUiState
import com.example.recipecompose.search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    query: String,
    viewModel: SearchViewModel = hiltViewModel(),
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel._uiState.collectAsState()
    viewModel.loadSearch(query = query)

    SearchContent(
        query = query,
        onBackPressed = {
            navHostController.popBackStack()
        },
        onClick = {
            navHostController.navigate(route = "")
        },
        searchUiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun SearchContent(
    query: String,
    onBackPressed: () -> Unit,
    onClick: (SearchUiData) -> Unit,
    searchUiState: SearchUiState,
    modifier: Modifier = Modifier,
) {
    Surface(color = Color.Black) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                }
                Spacer(Modifier.width(5.dp))
                Text(
                    text = query,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }
            LazyColumn {
                items(searchUiState.listSearch) {
                    CustomCardSearch(searchQueryDto = it, onClick = onClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchPreview() {

}