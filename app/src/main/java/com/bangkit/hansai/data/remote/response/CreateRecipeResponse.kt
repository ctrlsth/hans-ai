package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CreateRecipeResponse(
    @field:SerializedName("data")
    val data: RecipeId? = null,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class RecipeId(
    @field:SerializedName("id")
    val id: String
) : Parcelable
