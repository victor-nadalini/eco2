package com.example.eco2.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class MapViewModel: ViewModel() {
    // create a variable private for store location user and userLocation is a observer this location
    private val _userLocation = mutableStateOf<LatLng?>(null)
    val userLocation: State<LatLng?> = _userLocation

    // create a select location state save
    private val _selectLocation = mutableStateOf<LatLng?>(null)
    val selectLocation: State<LatLng?> = _selectLocation
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

    fun selectLocation(context: Context, selectPlace: String) {
        viewModelScope.launch {
            val geocoder = Geocoder(context)
            val addresses = withContext(Dispatchers.IO) {
               @Suppress("DEPRECATION") // check
                geocoder.getFromLocationName(selectPlace, 1 )
            }
            if (!addresses.isNullOrEmpty()) { // isNotEmpty is reading a number compare and need a string compare
                val address = addresses[0] // como vou passar a latitude longitude para o array ?
                val latLng = LatLng(address.latitude, address.longitude)
                _selectLocation.value = latLng
            } else {
                Timber.tag("MapScreen").e("No location found for the selected place.")
            }
        }
    }
}
