package com.bangkit.hansai.data.remote.retrofit

import com.bangkit.hansai.data.remote.request.CreateRecipeRequest
import com.bangkit.hansai.data.remote.request.GenRecipeRequest
import com.bangkit.hansai.data.remote.request.LoginRequest
import com.bangkit.hansai.data.remote.request.PredictCalorieRequest
import com.bangkit.hansai.data.remote.request.SaveLogRequest
import com.bangkit.hansai.data.remote.request.SaveRecipeRequest
import com.bangkit.hansai.data.remote.response.CreateRecipeResponse
import com.bangkit.hansai.data.remote.response.GeneratedResponse
import com.bangkit.hansai.data.remote.response.LogResponse
import com.bangkit.hansai.data.remote.response.LoginResponse
import com.bangkit.hansai.data.remote.response.PredictResponse
import com.bangkit.hansai.data.remote.response.RecipesResponse
import com.bangkit.hansai.data.remote.response.SaveLogResponse
import com.bangkit.hansai.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body reqBody: LoginRequest
    ): LoginResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): UserResponse

    @GET("recipes")
    suspend fun getRecipes(
        @Query("name") name: String? = null
    ): RecipesResponse

    @POST("recipes")
    suspend fun createRecipe(@Body reqBody: CreateRecipeRequest): CreateRecipeResponse

    @POST("recipes/generate")
    suspend fun generateRecipe(@Body reqBody: GenRecipeRequest): GeneratedResponse

    @GET("savedRecipes")
    suspend fun getSavedRecipes(): RecipesResponse

    @POST("savedRecipes")
    suspend fun saveRecipe(@Body reqBody: SaveRecipeRequest)

    @GET("food-logs")
    suspend fun getLogs(
        @Query("month") date: Int,
        @Query("year") type: Int
    ): LogResponse

    @GET("food-logs/{logId}")
    suspend fun getLogById(@Path("logId") logId: String): LogResponse

    @POST("food-logs")
    suspend fun saveLog(@Body reqBody: SaveLogRequest): SaveLogResponse

    @GET("predict/calorie-intake")
    suspend fun getCalorieIntake(@Body reqBody: PredictCalorieRequest): PredictResponse
}