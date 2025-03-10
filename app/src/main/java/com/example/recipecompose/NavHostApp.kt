package com.example.recipecompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipecompose.search.data.api.SearchService
import com.example.recipecompose.detail.presentation.ui.DetailScreen
import com.example.recipecompose.home.presentation.ui.HomeScreen
import com.example.recipecompose.search.presentation.ui.SearchScreen
import com.example.recipecompose.welcome.presentation.WelcomeScreen

@Composable
fun NavHostApp(
    service: SearchService,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "WelcomeScreen") {
        composable(route = "WelcomeScreen") {
            WelcomeScreen(navHostController = navController)
        }
        composable(route = "HomeScreen") {
            HomeScreen(modifier = modifier,navHostController = navController)
        }
        composable(
            route = "DetailScreen" + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            }
            )
        ) { backStackEntry ->
            val id = requireNotNull(backStackEntry.arguments?.getString("id"))
            DetailScreen(
                modifier = modifier,
                id = id.toInt(),
                navHostController = navController
            )
        }
        composable(route = "SearchScreen" + "/{query}",
            arguments = listOf(navArgument(name = "query") {
                type = NavType.StringType
            }
            )
        ) { backStackEntry ->
            val query = requireNotNull(backStackEntry.arguments?.getString("query"))
            SearchScreen(
                query = query,
                modifier = modifier,
                navHostController = navController
            )
        }
    }
}