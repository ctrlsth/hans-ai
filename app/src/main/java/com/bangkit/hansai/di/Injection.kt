package com.bangkit.hansai.di

import android.content.Context
import com.bangkit.hansai.data.local.preferences.UserPreferences
import com.bangkit.hansai.data.local.preferences.dataStore
import com.bangkit.hansai.data.local.room.database.HansAiDatabase
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
        val recipeDao = HansAiDatabase.getInstance(context).recipeDao()

        return RecipeRepository.getInstance(apiService, recipeDao)
    }

    fun provideLogRepository(context: Context): LogRepository {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(userPreferences)
        val logDao = HansAiDatabase.getInstance(context).logDao()

        return LogRepository.getInstance(apiService, logDao)
    }
}