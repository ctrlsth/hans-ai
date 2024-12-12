package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RecipesResponse(

    @field:SerializedName("data")
    val data: List<Recipe>,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class Recipe(

    @field:SerializedName("instructions")
    val instructions: String,

    @field:SerializedName("carbs")
    val carbs: Double,

    @field:SerializedName("protein")
    val protein: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("fat")
    val fat: Double,

    @field:SerializedName("ingredients")
    val ingredients: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("calories")
    val calories: Double
) : Parcelable
