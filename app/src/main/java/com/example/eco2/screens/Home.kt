package com.example.eco2.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onSettingsClick: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                actions = {
                    IconButton(onClick = onSettingsClick){
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "icon for configurations",
                            tint = Color.Green,
                            modifier = Modifier.size(41.dp)
                        )
                    }

                }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
        }
    }
}