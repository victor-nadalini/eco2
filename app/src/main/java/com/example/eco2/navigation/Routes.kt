package com.example.eco2.navigation

import com.example.eco2.R
data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: Int)
sealed class SingleRoutes (val route: String) {
    object Settings : SingleRoutes("config_route")
}
val topLevelRoutes = listOf(
    TopLevelRoute("Perfil", "perfil_route", R.drawable.perfil),
    TopLevelRoute("Favoritos", "favorites_route", R.drawable.favoritos),
    TopLevelRoute("Home", "home_route", R.drawable.map), //revise with documentation kotlin compose
    TopLevelRoute("Salvos", "save_route", R.drawable.salvos),
    TopLevelRoute("Notícias", "news_route", R.drawable.noticias),
)