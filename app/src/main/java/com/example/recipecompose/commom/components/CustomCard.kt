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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipecompose.home.data.remote.dto.RecipesDTO
import com.example.recipecompose.home.presentation.model.HomeUiData

@Composable
fun CustomCard(
    homeUiData: HomeUiData,
    onClick: (HomeUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {onClick(homeUiData)},
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
                model = homeUiData.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(homeUiData.title, color = Color.Black)
            Text("Overview", color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomCardPreview() {

}