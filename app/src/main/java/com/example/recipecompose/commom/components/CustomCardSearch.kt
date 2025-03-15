package com.example.recipecompose.commom.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipecompose.search.data.network.dto.SearchQueryDTO
import com.example.recipecompose.search.presentation.model.SearchUiData

@Composable
fun CustomCardSearch(
    searchQueryDto: SearchUiData,
    onClick: (SearchUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(searchQueryDto) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = searchQueryDto.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(searchQueryDto.title, color = Color.Black)
            Text("Overview", color = Color.Gray)
        }
    }
}
