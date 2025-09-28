package com.example.eco2.navigation

import com.example.eco2.R

sealed class AppScreen(val route: String, name: String, icon: Int)

object home : AppScreen("home_route","home",  R.drawable.map)
object favorites : AppScreen("favorites_route", "favorites",  R.drawable.favoritos)
object news : AppScreen("news_route","news",  R.drawable.noticias)
object perfil : AppScreen("perfil_route","perfil", R.drawable.perfil)
object save : AppScreen("save_route","save",  R.drawable.salvos)



