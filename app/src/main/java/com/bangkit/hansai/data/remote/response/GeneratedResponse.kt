package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GeneratedResponse(

    @field:SerializedName("data")
    val data: GenRecipe?,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class GenRecipe(

    @field:SerializedName("langkah")
    val langkah: String,

    @field:SerializedName("karbohidrat")
    val karbohidrat: Double,

    @field:SerializedName("bahan")
    val bahan: String,

    @field:SerializedName("protein")
    val protein: Double,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("lemak")
    val lemak: Double
) : Parcelable
