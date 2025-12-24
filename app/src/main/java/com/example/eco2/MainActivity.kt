package com.example.eco2

import Eco2Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.eco2.navigation.Navigation
import com.example.eco2.screens.Home
import com.example.eco2.utils.ManifestUtils
import com.example.eco2.viewmodel.MapViewModel
import com.google.android.libraries.places.api.Places



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val apiKey = ManifestUtils.getApiKeyManifest(this)

        if (!Places.isInitialized() && apiKey != null) {
            Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)
        }

        enableEdgeToEdge()
        setContent {
            Eco2Theme {
                Surface ( // folha de definiçoes iniciais = folha em branco com configurações
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Navigation()
                }
            }
        }
    }
}