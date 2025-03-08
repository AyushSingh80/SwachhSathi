package com.example.swachhsathi.ui.activity



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.swachhsathi.databinding.ActivityLoginBinding
import com.example.swachhsathi.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // When login button is clicked, initiate login.
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            userViewModel.loginUser(email, password)
        }

        // Link to RegistrationActivity for new users.
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        // Observe loading state to show/hide progress bar.
        userViewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        // Observe login result and react accordingly.
        userViewModel.loginState.observe(this) { result ->
            result.fold(onSuccess = { user ->
                Toast.makeText(this, "Login Successful. Welcome ${user.name}", Toast.LENGTH_SHORT).show()
                // Handle first-time login here (e.g., redirect to profile setup) if needed.
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, onFailure = { error ->
                Toast.makeText(this, "Login Failed: ${error.message}", Toast.LENGTH_LONG).show()
            })
        }
    }
}
