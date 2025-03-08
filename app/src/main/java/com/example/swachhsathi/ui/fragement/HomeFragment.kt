package com.example.swachhsathi.ui.fragement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swachhsathi.databinding.FragmentHomeBinding
import com.example.swachhsathi.ui.adapter.ComplaintAdapter
import com.example.swachhsathi.viewmodel.ComplaintViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val complaintViewModel: ComplaintViewModel by viewModels()
    private lateinit var adapter: ComplaintAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ComplaintAdapter(emptyList()) { complaint ->
            // Handle item click, e.g., open details dialog
        }
        binding.recyclerView.adapter = adapter

        // Observe data from ViewModel
        complaintViewModel.complaints.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
        // Assume token is managed elsewhere; passing null for Firebase
        complaintViewModel.fetchComplaints()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
