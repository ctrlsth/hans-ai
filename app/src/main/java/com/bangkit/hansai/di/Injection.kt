package com.bangkit.hansai.di

import android.content.Context
import com.bangkit.hansai.data.local.preferences.UserPreferences
import com.bangkit.hansai.data.local.preferences.dataStore
import com.bangkit.hansai.data.remote.retrofit.ApiConfig
import com.bangkit.hansai.data.repository.LogRepository
import com.bangkit.hansai.data.repository.RecipeRepository
import com.bangkit.hansai.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(userPreferences)

        return UserRepository.getInstance(apiService, userPreferences)
    }

    fun provideRecipeRepository(context: Context): RecipeRepository {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(userPreferences)

        return RecipeRepository.getInstance(apiService)
    }

    fun provideLogRepository(context: Context): LogRepository {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(userPreferences)

        return LogRepository.getInstance(apiService)
    }
}