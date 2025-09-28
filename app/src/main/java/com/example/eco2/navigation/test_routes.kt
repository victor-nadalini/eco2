package com.example.eco2.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco2.screens.Favorites
import com.example.eco2.screens.Home
import com.example.eco2.screens.News
import com.example.eco2.screens.Perfil
import com.example.eco2.screens.Save

@Composable
fun Routes() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { Navigation(navController = navController,)}
    ) {
            paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home_route",
            modifier = Modifier.padding(paddingValues)
        ) {
            // here is a define a strings "home route" for "Home()"
            composable("home_route") {Home()}
            composable("perfil_route") {Perfil()}
            composable("favorites_route") {Favorites()}
            composable("save_route") {Save()}
            composable("news_route") {News()}
        }
    }
}