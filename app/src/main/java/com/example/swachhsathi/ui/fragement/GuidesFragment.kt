package com.example.swachhsathi.ui.fragement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.swachhsathi.data.model.Guide
import com.example.swachhsathi.data.network.RetrofitInstance
import com.example.swachhsathi.databinding.FragmentGuidesBinding
import kotlinx.coroutines.launch

class GuidesFragment : Fragment() {
    private var _binding: FragmentGuidesBinding? = null
    private val binding get() = _binding!!
    private var guides: List<Guide> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGuidesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // For simplicity, fetch guides from the Flask API.
        lifecycleScope.launch {
            val response = RetrofitInstance.api.getGuides("Bearer your_token_here")
            if (response.isSuccessful) {
                guides = response.body() ?: listOf()
                // Bind guides data to UI (e.g., RecyclerView or simple list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
