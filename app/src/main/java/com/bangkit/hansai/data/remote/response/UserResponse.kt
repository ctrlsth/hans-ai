package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class UserResponse(

    @field:SerializedName("data")
    val data: User? = null,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class User(

    @field:SerializedName("allergies")
    val allergies: String,

    @field:SerializedName("physicalActivityLevel")
    val physicalActivityLevel: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("currentWeight")
    val currentWeight: Int,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("displayName")
    val displayName: String,

    @field:SerializedName("sleepQuality")
    val sleepQuality: String,

    @field:SerializedName("targetWeight")
    val targetWeight: Int,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("stressLevel")
    val stressLevel: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("age")
    val age: Int
) : Parcelable
