package com.example.charapedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charapedia.ui.screens.CharacterDetailScreen
import com.example.charapedia.ui.screens.home.HomeScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ){

        composable(
            route = Route.Home.route
        ) {
            HomeScreen(
                goToCharacterDetail = { malId : Int ->
                    navController.navigate(
                        Route.CharacterDetail.createRoute(malId)
                    )
                }
            )
        }

        composable(
            route = Route.CharacterDetail.route,
            arguments = listOf(
                navArgument("malId"){
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->

            val malId = navBackStackEntry.arguments?.getInt("malId") ?: 0

            CharacterDetailScreen(
                malId = malId,
                goBack = {
                    navController.popBackStack()
                }
            )

        }

    }

}