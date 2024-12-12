package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LogResponse(

    @field:SerializedName("data")
    val data: List<Log>,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class LogItems(
    // Log Items ID
    @field:SerializedName("id")
    val id: String,

    // Parent Log ID
    @field:SerializedName("foodLogId")
    val foodLogId: String,

    // Breakfast/Lunch/Dinner
    @field:SerializedName("type")
    val type: String,

    // Recipe's ID
    @field:SerializedName("recipeId")
    val recipeId: String,

    // Recipe Object
    @field:SerializedName("recipe")
    val recipe: Recipe,
) : Parcelable

@Parcelize
data class Log(

    @field:SerializedName("totalCarbs")
    val totalCarbs: Int,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("FoodLogItems")
    val foodLogItems: List<LogItems>,

    @field:SerializedName("logTitle")
    val logTitle: String,

    @field:SerializedName("totalFat")
    val totalFat: Int,

    @field:SerializedName("totalProtein")
    val totalProtein: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("baseGOal")
    val baseGOal: Int,

    @field:SerializedName("weight")
    val weight: Int,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("totalCalories")
    val totalCalories: Int,

    @field:SerializedName("userId")
    val userId: String
) : Parcelable
