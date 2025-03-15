package com.example.recipecompose.detail.data.model

import com.example.recipecompose.detail.presentation.model.DetailUiData

data class DetailDataLayer(
    val image: String,
    val title: String,
    val summary: String
)

fun DetailDataLayer.toDetailUiData(): DetailUiData {
    return DetailUiData(
        image = image,
        title = title,
        summary = summary
    )
}
