package com.example.swachhsathi.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.swachhsathi.data.model.User
import com.example.swachhsathi.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository()

    // LiveData for registration result (success/failure)
    private val _registrationState = MutableLiveData<Result<User>>()
    val registrationState: LiveData<Result<User>> get() = _registrationState

    // LiveData for login result (success/failure)
    private val _loginState = MutableLiveData<Result<User>>()
    val loginState: LiveData<Result<User>> get() = _loginState

    // LiveData to indicate loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun registerUser(name: String, email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.registerUser(name, email, password)
            _registrationState.value = result
            _isLoading.value = false
        }
    }

    fun loginUser(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.loginUser(email, password)
            _loginState.value = result
            _isLoading.value = false
        }
    }
}
