package com.example.swachhsathi.data.model

import com.google.android.gms.maps.model.LatLng

data class Complaint(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val location: LatLng,  // Google Maps LatLng
    val status: String = "Pending", // Pending, In Progress, Resolved
    val reporterId: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
