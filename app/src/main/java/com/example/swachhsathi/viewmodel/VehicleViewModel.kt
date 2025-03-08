package com.example.swachhsathi.viewmodel


import androidx.lifecycle.*
import com.example.swachhsathi.data.model.Vehicle
import com.example.swachhsathi.data.network.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {
    private val _vehicleLocation = MutableLiveData<Vehicle>()
    val vehicleLocation: LiveData<Vehicle> = _vehicleLocation

    init {
        // Simulate periodic vehicle location updates every 15 seconds
        viewModelScope.launch {
            while (true) {
                // Call your API service (or simulate)
                val response = RetrofitInstance.api.getVehicleLocation().body()
                response?.let {
                    // Map the response to a Vehicle object (simplified)
                    val lat = it.location["lat"] ?: 0.0
                    val lng = it.location["lng"] ?: 0.0
                    _vehicleLocation.postValue(
                        Vehicle(
                            id = "vehicle_1",
                            currentLocation = com.google.android.gms.maps.model.LatLng(lat, lng),
                            estimatedArrival = it.estimated_arrival
                        )
                    )
                }
                delay(15000) // 15 seconds
            }
        }
    }
}
