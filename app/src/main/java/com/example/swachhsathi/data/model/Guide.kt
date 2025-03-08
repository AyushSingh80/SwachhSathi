package com.example.swachhsathi.data.model


data class Guide(
    val id: String = "",
    val category: String = "",   // e.g., "Wet Waste", "Dry Waste", "Recyclables"
    val title: String = "",
    val description: String = "",
    val imageUrl: String = ""
)
