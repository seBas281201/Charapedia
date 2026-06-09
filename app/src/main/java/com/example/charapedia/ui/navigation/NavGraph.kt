package com.example.charapedia.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charapedia.ui.screens.CharacterDetailScreen
import com.example.charapedia.ui.screens.HomeScreen
import com.example.charapedia.ui.screens.SettingScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ){

        composable(
            route = Route.Home.route,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut() + scaleOut()
            }
        ) {
            HomeScreen(
                goToCharacterDetail = { malId : Int ->
                    navController.navigate(
                        Route.CharacterDetail.createRoute(malId)
                    )
                },
                goToSettings = {
                    navController.navigate(
                        Route.Settings.route
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
            ),
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut() + scaleOut()
            }
        ) { navBackStackEntry ->

            val malId = navBackStackEntry.arguments?.getInt("malId") ?: 0

            CharacterDetailScreen(
                malId = malId,
                goBack = {
                    navController.popBackStack()
                },
                goToSettings = {
                    navController.navigate(
                        Route.Settings.route
                    )
                }
            )

        }

        composable(
            route = Route.Settings.route,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut() + scaleOut()
            }
        ){

            SettingScreen(
                goBack = {
                    navController.popBackStack()
                }
            )

        }

    }

}