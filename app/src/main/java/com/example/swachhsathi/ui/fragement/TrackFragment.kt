package com.example.swachhsathi.ui.fragement



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swachhsathi.databinding.FragmentTrackBinding
import com.example.swachhsathi.viewmodel.VehicleViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TrackFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentTrackBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap
    private val vehicleViewModel: VehicleViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(com.example.swachhsathi.R.id.mapContainer) as SupportMapFragment
        mapFragment.getMapAsync(this)
        observeVehicleUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Optionally customize map UI
    }

    private fun observeVehicleUpdates() {
        vehicleViewModel.vehicleLocation.observe(viewLifecycleOwner) { vehicle ->
            val position = vehicle.currentLocation
            map.clear()
            map.addMarker(MarkerOptions().position(position).title("Vehicle"))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 14f))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

