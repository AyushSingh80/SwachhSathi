package com.example.swachhsathi.data.model


import com.google.android.gms.maps.model.LatLng

data class Vehicle(
    val id: String = "",
    val currentLocation: LatLng,
    val routeHistory: List<LatLng> = listOf(),
    val estimatedArrival: String = ""
)
