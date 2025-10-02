package com.example.eco2.navigation

import com.example.eco2.R
data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: Int)
val topLevelRoutes = listOf(
    TopLevelRoute("Home", "home_route", R.drawable.map),
    TopLevelRoute("Perfil", "perfil_route", R.drawable.perfil),
    TopLevelRoute("Favoritos", "favorites_route", R.drawable.favoritos),
    TopLevelRoute("Salvos", "save_route", R.drawable.salvos),
    TopLevelRoute("Notícias", "news_route", R.drawable.noticias),
)