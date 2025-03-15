package com.example.recipecompose.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipecompose.detail.data.network.dto.RecipedesDetailDto
import com.example.recipecompose.detail.presentation.model.DetailUiData
import com.example.recipecompose.detail.presentation.viewmodel.DetailViewModel


@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadRecipeDetail(id)



    uiState.detailDto?.let {
        DetailContent(detailUiData = it, onBackPressed = {
            navHostController.popBackStack()
        }, modifier = modifier)
    }
}

@Composable
private fun DetailContent(
    detailUiData: DetailUiData,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = Color.Black) {
        Column(modifier = modifier.fillMaxSize() ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                }
                Spacer(Modifier.width(5.dp))
                Text(
                    text = detailUiData.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }
            AsyncImage(
                model = detailUiData.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
            Text(text = detailUiData.summary, fontSize = 12.sp, color = Color.White, maxLines = 5)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {

}