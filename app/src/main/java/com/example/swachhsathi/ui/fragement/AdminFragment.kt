package com.example.swachhsathi.ui.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.swachhsathi.R
import com.example.swachhsathi.databinding.FragmentAdminBinding

class AdminFragment : Fragment() {

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Enable toolbar with back button
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Admin Panel"
            setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        // Button Click Listeners
        binding.manageUsersButton.setOnClickListener {
            findNavController().navigate(R.id.manageUsersButton)
        }

        binding.viewReportsButton.setOnClickListener {
            findNavController().navigate(R.id.viewReportsButton)
        }

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.settingsButton)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
