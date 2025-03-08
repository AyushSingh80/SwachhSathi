package com.example.swachhsathi.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.swachhsathi.databinding.ActivityRegistrationBinding
import com.example.swachhsathi.viewmodel.UserViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // When register button is clicked, call registerUser() on the ViewModel.
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmailReg.text.toString().trim()
            val password = binding.etPasswordReg.text.toString().trim()
            userViewModel.registerUser(name, email, password)
        }

        // Link back to LoginActivity if the user already has an account.
        binding.tvLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Observe loading state to show/hide progress bar.
        userViewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        // Observe registration result and react accordingly.
        userViewModel.registrationState.observe(this) { result ->
            result.fold(onSuccess = { user ->
                Toast.makeText(this, "Registration Successful. Welcome ${user.name}", Toast.LENGTH_SHORT).show()
                // For first-time login, you might want to redirect to a profile setup screen.
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, onFailure = { error ->
                Toast.makeText(this, "Registration Failed: ${error.message}", Toast.LENGTH_LONG).show()
            })
        }
    }
}
