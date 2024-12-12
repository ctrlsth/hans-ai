package com.bangkit.hansai.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.hansai.data.remote.request.CreateRecipeRequest
import com.bangkit.hansai.data.remote.request.GenRecipeRequest
import com.bangkit.hansai.data.remote.response.GenRecipe
import com.bangkit.hansai.data.remote.response.RecipesResponse
import com.bangkit.hansai.data.remote.retrofit.ApiService

class RecipeRepository private constructor(
    private val apiService: ApiService
) {
    fun getRecipes(name: String? = null): LiveData<Result<RecipesResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipes(name)
            if (response.data.isEmpty()) {
                emit(Result.Error("No data found"))
            } else {
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun createRecipes(reqBody: CreateRecipeRequest): Result<String> {
        return try {
            val response = apiService.createRecipe(reqBody)
            if (response.isSuccess) {
                Result.Success(response.data?.id ?: "")
            } else {
                Result.Error(response.messages.toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    fun generateRecipes(reqBody: GenRecipeRequest): LiveData<Result<GenRecipe>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.generateRecipe(reqBody)
            if (response.isSuccess) {
                emit(Result.Success(response.data!!))
            } else {
                emit(Result.Error(response.messages.toString()))
            }
        } catch (e: Exception) {
            Log.d("RecipeRepository", e.toString())
            Result.Error(e.message.toString())
        }
    }

    companion object {
        @Volatile
        private var instance: RecipeRepository? = null
        fun getInstance(
            apiService: ApiService
        ): RecipeRepository =
            instance ?: synchronized(this) {
                instance ?: RecipeRepository(apiService)
            }.also { instance = it }
    }
}