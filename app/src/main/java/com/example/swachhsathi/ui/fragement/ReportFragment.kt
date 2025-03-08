package com.example.swachhsathi.ui.fragement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.databinding.FragmentReportBinding
import com.example.swachhsathi.viewmodel.ComplaintViewModel
import com.google.android.gms.maps.model.LatLng

class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val complaintViewModel: ComplaintViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSubmitComplaint.setOnClickListener {
            // For simplicity, assume static location; integrate Google Maps or LocationManager as needed.
            val complaint = Complaint(
                title = binding.tvReportTitle.text.toString().trim(),
                description = binding.etDescription.text.toString().trim(),
                imageUrl = "https://example.com/image.png", // Replace with actual image URL after upload
                location = LatLng(12.9716, 77.5946)
            )
            complaintViewModel.submitComplaint(complaint)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
