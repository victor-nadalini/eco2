package com.example.eco2.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.eco2.components.Searchbar
import com.example.eco2.viewmodel.MapViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onSettingsClick: () -> Unit, mapViewModel: MapViewModel) {

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
            // variable of position the google maps
            val cameraPositionState = rememberCameraPositionState()
            val context = LocalContext.current
            // observe user location from the view model
            val userLocation by mapViewModel.userLocation
            val selectLocation by mapViewModel.selectLocation
            val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
            // variable permission user check and create a contract permission
            val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()
            ) {
                isGranted ->
                if (isGranted) {
                    mapViewModel.searchLocationUser(context, fusedLocationClient)
                } else {
                    Timber.e("Location permission was denied by the user.")
                }
            }
            // request permission when the composable is launched
            LaunchedEffect(Unit) {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                        mapViewModel.searchLocationUser(context, fusedLocationClient)
                    }
                    else -> {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }
            }
            // display google maps
            GoogleMap (
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                userLocation?.let {
                    Marker(
                        state = MarkerState(position = it),
                        title = "Your Location",
                        snippet = "This is where you are currently located."
                    )
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(18.dp))

                Searchbar(
                    onPlaceSelected = { selectPlace ->
                        mapViewModel.selectLocation(context, selectPlace) // error possible is here
                    },
                )

                selectLocation?.let {
                    Marker(
                        state = MarkerState(position = it),
                        title = "Selected Location",
                        snippet = "This is the place you selected."
                    )

                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                }
           }
        }
    }
}