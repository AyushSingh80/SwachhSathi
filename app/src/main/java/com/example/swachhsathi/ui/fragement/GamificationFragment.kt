package com.example.swachhsathi.ui.fragement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.swachhsathi.data.network.RetrofitInstance
import com.example.swachhsathi.databinding.FragmentGamificationBinding
import kotlinx.coroutines.launch

class GamificationFragment : Fragment() {
    private var _binding: FragmentGamificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGamificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Fetch gamification data from API
        lifecycleScope.launch {
            val response = RetrofitInstance.api.getGamificationData("Bearer your_token_here")
            if (response.isSuccessful) {
                val data = response.body()
                // Bind data: Show badges, leaderboard, and progress (this is a sample implementation)
                binding.tvProgressLabel.text = "Progress: ${data?.progress ?: 0}%"
                // Populate leaderboard RecyclerView if needed
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
