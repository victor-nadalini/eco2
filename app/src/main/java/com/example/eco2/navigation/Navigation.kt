package com.example.eco2.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eco2.screens.Config
import com.example.eco2.screens.Favorites
import com.example.eco2.screens.Home
import com.example.eco2.screens.News
import com.example.eco2.screens.Perfil
import com.example.eco2.screens.Save
import com.example.eco2.viewmodel.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(67.dp),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    topLevelRoutes.forEach {
                        topLevelRoute ->
                        val select = currentDestination?.hierarchy?.any {it.route == topLevelRoute.route} == true
                        IconButton(
                            onClick = {
                                navController.navigate(topLevelRoute.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(topLevelRoute.icon),
                                contentDescription = topLevelRoute.name,
                                modifier = Modifier.size(30.dp),
                                tint = if (select) Color.Unspecified else Color.Unspecified // provisors
                            )
                        }
                    }
                }

            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home_route",
            modifier = Modifier.padding(innerPadding)
        ) {
            // here is a define a strings "home route" for "Home()"
            composable("home_route") {
                val mapViewModel: MapViewModel = viewModel()
                Home(
                    onSettingsClick = { navController.navigate(SingleRoutes.Settings.route) },
                    mapViewModel = mapViewModel,
                    ) }
            composable("perfil_route") {Perfil()}
            composable("favorites_route") {Favorites()}
            composable("save_route") {Save()}
            composable("news_route") {News()}
            composable(SingleRoutes.Settings.route) { Config() }}
    }
}
