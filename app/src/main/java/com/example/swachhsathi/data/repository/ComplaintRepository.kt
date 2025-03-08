package com.example.swachhsathi.data.repository

import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.data.network.RetrofitInstance
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.tasks.await
import retrofit2.Response

class ComplaintRepository(private val useFirebase: Boolean) {
    // For Firebase implementation:
//    private val firestore = FirebaseFirestore.getInstance()
//    private val complaintsCollection = firestore.collection("complaints")

    // For Flask API implementation:
    private val apiService = RetrofitInstance.api

    //    suspend fun getComplaints(userToken: String? = null): List<Complaint> {
////        return if (useFirebase) {
////            // Firebase: Query Firestore
////            val snapshot = complaintsCollection.get().await()
////            snapshot.toObjects(Complaint::class.java)
////        } else {
////            // Flask API: Use Retrofit call
////            apiService.getComplaints("Bearer $userToken").body() ?: emptyList()
////        }
//
//    }
    suspend fun getComplaints(userToken: String? = null): List<Complaint> {
        return RetrofitInstance.api.getComplaints("Bearer $userToken").body() ?: emptyList()
    }


    //    suspend fun postComplaint(complaint: Complaint, userToken: String? = null): Complaint? {
//        return if (useFirebase) {
//            // Add to Firestore
//            val docRef = complaintsCollection.document()
//            val complaintWithId = complaint.copy(id = docRef.id)
//            docRef.set(complaintWithId).await()
//            complaintWithId
//        } else {
//            apiService.postComplaint("Bearer $userToken", complaint).body()
//        }
//    }
//
//    suspend fun updateComplaintStatus(
//        complaintId: String,
//        newStatus: String,
//        userToken: String? = null
//    ): Complaint? {
//        return if (useFirebase) {
//            val docRef = complaintsCollection.document(complaintId)
//            docRef.update("status", newStatus).await()
//            // Fetch the updated complaint
//            docRef.get().await().toObject(Complaint::class.java)
//        } else {
//            val statusUpdate = mapOf("status" to newStatus)
//            apiService.updateComplaintStatus("Bearer $userToken", complaintId, statusUpdate).body()
//        }
//    }
//
//    // Delete a complaint
//    suspend fun deleteComplaint(complaintId: String, userToken: String? = null): Boolean {
//        return if (useFirebase) {
//            complaintsCollection.document(complaintId).delete().await()
//            true
//        } else {
//            val response = apiService.deleteComplaint("Bearer $userToken", complaintId)
//            response.isSuccessful
//        }
//    }
//}
    suspend fun postComplaint(complaint: Complaint, userToken: String?=null): Complaint? {
        return apiService.postComplaint("Bearer $userToken", complaint).body()
    }

    suspend fun updateComplaintStatus(
        complaintId: String,
        newStatus: String,
        userToken: String
    ): Complaint? {
        val statusUpdate = mapOf("status" to newStatus)
        return apiService.updateComplaintStatus("Bearer $userToken", complaintId, statusUpdate)
            .body()
    }

    suspend fun deleteComplaint(complaintId: String, userToken: String): Boolean {
        val response = apiService.deleteComplaint("Bearer $userToken", complaintId)
        return response.isSuccessful
    }

}
