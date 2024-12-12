package com.bangkit.hansai.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.hansai.data.remote.response.User
import com.bangkit.hansai.data.repository.Result
import com.bangkit.hansai.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    init {
        login()
    }

    private val _login = MutableLiveData(false)
    val login: LiveData<Boolean> = _login

    private fun login() {
        viewModelScope.launch {
            val result = userRepository.login()
            if (result is Result.Success) {
                _login.value = true
                Log.d("HomeViewModel", "login: ${result.data}")
            } else {
                _login.value = false
                Log.d("HomeViewModel", "login: $result")
            }
        }
    }

    fun getProfile(): LiveData<Result<User>> {
        return userRepository.getProfile()
    }
}