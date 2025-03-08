package com.example.swachhsathi.data.model

data class User(
    val id: String = "",
    val name: String = "",
    val password: String = "",
    val email: String = "",
    val role: String = "Citizen"  // Citizen, MunicipalOfficial, Supervisor
)
