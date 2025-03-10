package com.example.recipecompose.commom.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomSearch(
    query: String,
    changeQuery: (String) -> Unit,
    toggleSearchQuery: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Red,
        ),
        textStyle = MaterialTheme.typography.labelMedium.copy(Color.White),
        maxLines = 1,
        value = query ,
        placeholder = {
            Text("Search recipes", color = Color.White)
        },
        onValueChange = changeQuery,
        leadingIcon = {
            IconButton(
                onClick = {toggleSearchQuery(query)}
            )
            {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
            }
        }
    )
}

@Preview
@Composable
private fun CustomSearchPreview() {
    
}