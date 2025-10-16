package com.example.eco2.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.StateFlow
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import timber.log.Timber


class MapViewModel: ViewModel() {
    // create a variable private for store location user and userLocation is a observer this location
    private val _userLocation = mutableStateOf<LatLng?>(null)
    val userLocation: State<LatLng?> = _userLocation
    // function search the location user and update a state user location
    fun searchLocationUser(context: Context, fusedLocationClient: FusedLocationProviderClient) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                // function call the last location user and update in te private variable location
                fusedLocationClient.lastLocation.addOnSuccessListener { location -> location?.let {
                    val userLatLng = LatLng(it.latitude, it.longitude)
                    _userLocation.value = userLatLng
                }}
            } catch (e: SecurityException) {
                Timber.e("Permission for location access was revoked: ${e.localizedMessage}")
            }
        } else {
            Timber.e("Location permission is not granted.")
        }
    }
}
