package com.example.recipecompose.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.recipecompose.data.remote.api.SpoonacularService
import com.example.recipecompose.data.remote.dto.SearchQueryDTO
import com.example.recipecompose.presentation.components.CustomCardSearch

@Composable
fun SearchScreen(
    query: String,
    service: SpoonacularService,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var listSearchQueryDTO by remember { mutableStateOf<List<SearchQueryDTO>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = service.getSearchQuery(query)
            if (response.isSuccessful){
                response.body()?.results?.let {
                    listSearchQueryDTO = it
                }
            }else{
                Log.e("SearchScreen", "Error code: ${response.code()} - Error body: ${response.errorBody()}")
            }
        }catch (e: Exception){
            Log.e("SearchScreen", "Exception: $e")
        }
    }

    SearchContent(
        query = query,
        onBackPressed = {
            navHostController.popBackStack()
        },
        onClick = {
            navHostController.navigate(route = "")
        },
        listSearchQueryDTO = listSearchQueryDTO,
        modifier = modifier
    )
}

@Composable
private fun SearchContent(
    query: String,
    onBackPressed: () -> Unit,
    onClick: (SearchQueryDTO) -> Unit,
    listSearchQueryDTO: List<SearchQueryDTO>,
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
                items(listSearchQueryDTO) {
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