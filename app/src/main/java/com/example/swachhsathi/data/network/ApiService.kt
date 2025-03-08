package com.example.swachhsathi.data.network

import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.data.model.User
import com.example.swachhsathi.data.model.Guide
//import com.google.android.gms.common.api.Response
import retrofit2.Response
import retrofit2.http.*

data class AuthResponse(val access_token: String, val user: User)

data class VehicleTrackingResponse(
    val location: Map<String, Double>, // keys "lat" and "lng"
    val timestamp: Long,
    val estimated_arrival: String
)

data class GamificationData(
    val badges: List<String>,
    val leaderboard: List<User>,
    val progress: Int
)

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body user: User): Response<AuthResponse>

    @POST("api/login")
    suspend fun login(@Body credentials: Map<String, String>): Response<AuthResponse>

    @GET("api/complaints")
    suspend fun getComplaints(@Header("Authorization") token: String): Response<List<Complaint>>

    @POST("api/complaints")
    suspend fun postComplaint(
        @Header("Authorization") token: String,
        @Body complaint: Complaint
    ): Response<Complaint>

    @PUT("api/complaints/{id}")
    suspend fun updateComplaintStatus(
        @Header("Authorization") token: String,
        @Path("id") complaintId: String,
        @Body statusUpdate: Map<String, String>
    ): Response<Complaint>

    @DELETE("api/complaints/{id}")
    suspend fun deleteComplaint(
        @Header("Authorization") token: String,
        @Path("id") complaintId: String
    ): Response<Map<String, String>>

    // Vehicle Tracking endpoint
    @GET("api/vehicle-tracking")
    suspend fun getVehicleLocation(): Response<VehicleTrackingResponse>

    // Guides endpoint
    @GET("api/guides")
    suspend fun getGuides(@Header("Authorization") token: String): Response<List<Guide>>

    // Gamification endpoints
    @GET("api/gamification")
    suspend fun getGamificationData(@Header("Authorization") token: String): Response<GamificationData>
}
