package com.bangkit.hansai.data.remote.request

import com.google.gson.annotations.SerializedName

// Login
data class LoginRequest(
    val email: String,
    val password: String
)


// Recipes
data class CreateRecipeRequest(
    val name: String,
    val ingredients: String,
    val instructions: String,
    val carbs: Double,
    val protein: Double,
    val fat: Double,
    val calories: Double
)

data class GenRecipeRequest(
    val ingredients: List<String>
)

data class SaveRecipeRequest(
    val recipeId: String
)


// Logs
data class SaveLogRequest(
    @field:SerializedName("log_title")
    val logTitle: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("total_protein")
    val totalProtein: Double,

    @field:SerializedName("total_carbs")
    val totalCarbs: Double,

    @field:SerializedName("total_fat")
    val totalFat: Double,

    @field:SerializedName("total_calories")
    val totalCalories: Double,

    @field:SerializedName("base_goal")
    val baseGoal: Double,

    @field:SerializedName("weight")
    val weight: Double,

    @field:SerializedName("food_items")
    val foodItems: List<FoodItem>
)

data class FoodItem(
    val recipeId: String,
    val type: String
)


// Predict Calorie Intake
data class PredictCalorieRequest(
    val age: Int,
    val gender: String,
    val dailyCaloriesConsumed: Double,
    val weightChangeInLbs: Double,
    val durationInWeeks: Int,
    val physicalActivityLevel: String,
    val sleepQuality: String,
    val stressLevel: Int,
    val currentWeightInLbs: Double,
    val caloricAdjustment: Double
)




