package com.example.eco2

import Eco2Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco2.navigation.Navigation
import com.example.eco2.screens.Favorites
import com.example.eco2.screens.Home
import com.example.eco2.screens.News
import com.example.eco2.screens.Perfil
import com.example.eco2.screens.Save

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            Eco2Theme {
                Surface ( // folha de definiçoes iniciais = folha em branco com configurações
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ){
                    @Composable
                    fun Routes() {

                        val navController = rememberNavController()

                        Scaffold(
                            bottomBar = { Navigation(navController = navController) } // connect navigation botton bar
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
                    Routes() // now code running and when open emulator the app crash
                }
            }
        }
    }
}