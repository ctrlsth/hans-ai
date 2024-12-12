package com.bangkit.hansai.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.hansai.data.local.preferences.UserPreferences
import com.bangkit.hansai.data.remote.request.LoginRequest
import com.bangkit.hansai.data.remote.request.PredictCalorieRequest
import com.bangkit.hansai.data.remote.response.User
import com.bangkit.hansai.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {
    suspend fun login(): Result<String> {
        return try {
            val response = apiService.login(LoginRequest("tes@gmail.com", "string"))
            val token = response.data?.token ?: ""
            val id = response.data?.id ?: ""
            Log.d("UserRepository", "login: ${response.isSuccess}")
            userPreferences.saveUserToken(token)
            userPreferences.saveUserId(id)
            Result.Success(token)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    fun getProfile(): LiveData<Result<User>> = liveData {
        emit(Result.Loading)
        try {
            Log.d("UserRepository", "getProfile: ${userPreferences.getUserId().firstOrNull()}")
            val response = apiService.getUser(userPreferences.getUserId().first())
            if (response.data != null) {
                val user = response.data
                emit(Result.Success(user))
            } else {
                Log.d("UserRepository", "getProfile: ${response.messages} ")
                emit(Result.Error(response.messages.toString()))
            }
        } catch (e: Exception) {
            Log.d("UserRepository", e.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getCalorieIntake(reqBody: PredictCalorieRequest): LiveData<Result<Double>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getCalorieIntake(reqBody)
                if (response.isSuccess) {
                    emit(Result.Success(response.prediction!!))
                } else {
                    emit(Result.Error(response.messages.toString()))
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreferences: UserPreferences,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreferences)
            }.also { instance = it }
    }
}