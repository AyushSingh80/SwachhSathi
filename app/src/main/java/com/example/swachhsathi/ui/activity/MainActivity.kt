package com.example.swachhsathi.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swachhsathi.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Bottom Navigation with NavController
        val navController = findNavController(com.example.swachhsathi.R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        // Fetch current user role from SharedPreferences (saved in LoginActivity)
        val prefs = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val currentUserRole = prefs.getString("USER_ROLE", "Citizen") ?: "Citizen"

        // Conditionally display the Admin tab if role is MunicipalOfficial or Supervisor
        if (currentUserRole == "Citizen") {
            binding.bottomNavigationView.menu.findItem(com.example.swachhsathi.R.id.nav_admin)?.isVisible = false
        }
    }
}
