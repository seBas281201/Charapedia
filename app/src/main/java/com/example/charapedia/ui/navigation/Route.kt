package com.example.charapedia.ui.navigation

sealed class Route(val route: String) {
    data object Home : Route(route = "home/")
    data object Settings : Route(route = "settings/")
    data object CharacterDetail : Route(route = "character/{malId}"){
        fun createRoute(malId : Int) : String = "character/$malId"
    }
}