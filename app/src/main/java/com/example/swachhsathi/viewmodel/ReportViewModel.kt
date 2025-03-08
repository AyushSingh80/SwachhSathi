package com.example.swachhsathi.viewmodel



import android.app.Application
import androidx.lifecycle.*
import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.data.repository.ComplaintRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class ReportViewModel(application: Application) : AndroidViewModel(application) {

    // Toggle useFirebase as needed
    private val repository = ComplaintRepository(useFirebase = true)

    // LiveData to expose the submission result (a successful Complaint or an error)
    private val _submitState = MutableLiveData<Result<Complaint>>()
    val submitState: LiveData<Result<Complaint>> get() = _submitState

    // LiveData to indicate whether a submission is in progress
    private val _isSubmitting = MutableLiveData<Boolean>()
    val isSubmitting: LiveData<Boolean> get() = _isSubmitting

    /**
     * Submits a new complaint.
     *
     * @param category Category or title of the complaint.
     * @param description Detailed description of the complaint.
     * @param imageUrl URL of the uploaded image (or local path if handled differently).
     * @param location Location as a LatLng object.
     * @param reporterId The ID of the reporter (can be fetched from user preferences or passed in).
     */
    fun submitComplaint(
        category: String,
        description: String,
        imageUrl: String,
        location: LatLng,
        reporterId: String
    ) {
        _isSubmitting.value = true
        viewModelScope.launch {
            try {
                // Create a Complaint object with the provided details
                val complaint = Complaint(
                    id = "", // The repository can assign a unique ID (for example, via Firestore or auto-generated in SQLite)
                    title = category,
                    description = description,
                    imageUrl = imageUrl,
                    location = location,
                    status = "Pending", // Default status
                    reporterId = reporterId,
                    timestamp = System.currentTimeMillis()
                )
                // Submit the complaint using the repository
                val result = repository.postComplaint(complaint)
                if (result != null) {
                    _submitState.value = Result.success(result)
                } else {
                    _submitState.value = Result.failure(Exception("Submission failed"))
                }
            } catch (e: Exception) {
                _submitState.value = Result.failure(e)
            } finally {
                _isSubmitting.value = false
            }
        }
    }
}
