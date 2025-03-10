package com.example.recipecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.recipecompose.data.remote.api.SpoonacularService
import com.example.recipecompose.presentation.theme.RecipeComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var service: SpoonacularService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostApp(modifier = Modifier.padding(innerPadding), service = service)
                }
            }
        }
    }
}

