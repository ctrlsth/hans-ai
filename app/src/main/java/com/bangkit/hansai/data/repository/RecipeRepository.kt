package com.bangkit.hansai.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.data.local.room.dao.RecipeDao
import com.bangkit.hansai.data.remote.request.CreateRecipeRequest
import com.bangkit.hansai.data.remote.request.GenRecipeRequest
import com.bangkit.hansai.data.remote.response.GenRecipe
import com.bangkit.hansai.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository private constructor(
    private val apiService: ApiService,
    private val recipeDao: RecipeDao
) {

    fun getRecipes(name: String? = null): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipes(name)
            if (response.data.isEmpty()) {
                emit(Result.Error("No data found"))
            } else {
                val recipeList = response.data.map { recipe ->
                    RecipeEntity(
                        id = recipe.id,
                        name = recipe.name,
                        ingredients = recipe.ingredients,
                        instructions = recipe.instructions,
                        carbs = recipe.carbs,
                        protein = recipe.protein,
                        fat = recipe.fat,
                        calories = recipe.calories
                    )
                }
                withContext(Dispatchers.IO) {
                    recipeDao.insert(recipeList)
                }
            }
        } catch (e: Exception) {
            Log.d("RecipeRepository", e.toString())
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<RecipeEntity>>> =
            recipeDao.getAll().map { Result.Success(it) }
        emitSource(localData)
        Log.d("RecipeRepository", "Data submitted to adapter: $localData")
    }

    fun searchRecipes(query: String): LiveData<List<RecipeEntity>> {
        return recipeDao.getByName("%${query}%")
    }

    suspend fun createRecipe(reqBody: CreateRecipeRequest): Result<String> {
        return try {
            val response = apiService.createRecipe(reqBody)
            if (response.isSuccess) {
                Result.Success(response.data?.id ?: "")
            } else {
                Log.d("RecipeRepository", response.messages.toString())
                Result.Error(response.messages.toString())
            }
        } catch (e: Exception) {
            Log.d("RecipeRepository", e.toString())
            Result.Error(e.message.toString())
        }
    }

    fun generateRecipe(reqBody: GenRecipeRequest): LiveData<Result<GenRecipe>> = liveData {
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
            apiService: ApiService,
            recipeDao: RecipeDao
        ): RecipeRepository =
            instance ?: synchronized(this) {
                instance ?: RecipeRepository(apiService, recipeDao)
            }.also { instance = it }
    }
}