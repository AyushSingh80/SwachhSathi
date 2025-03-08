package com.example.swachhsathi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.data.repository.ComplaintRepository

class ComplaintViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ComplaintRepository(useFirebase = true) // Toggle to false for Flask
    private val _complaints = MutableLiveData<List<Complaint>>()
    val complaints: LiveData<List<Complaint>> = _complaints

    fun fetchComplaints(userToken: String? = null) {
        viewModelScope.launch {
            try {
                val result = repository.getComplaints(userToken)
                _complaints.postValue(result)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun submitComplaint(complaint: Complaint, userToken: String? = null) {
        viewModelScope.launch {
            try {
                if (userToken != null) {
                    repository.postComplaint(complaint, userToken)
                }
                fetchComplaints(userToken)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    fun updateStatus(complaintId: String, newStatus: String, userToken: String? = null) {
        viewModelScope.launch {
            try {
                if (userToken != null) {
                    repository.updateComplaintStatus(complaintId, newStatus, userToken)
                }
                fetchComplaints(userToken)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    fun deleteComplaint(complaintId: String, userToken: String? = null) {
        viewModelScope.launch {
            try {
                if (userToken != null) {
                    repository.deleteComplaint(complaintId, userToken)
                }
                fetchComplaints(userToken)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
