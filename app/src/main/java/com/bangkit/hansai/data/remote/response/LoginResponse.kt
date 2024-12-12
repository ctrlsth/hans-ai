package com.bangkit.hansai.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
)

data class Data(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("token")
    val token: String
)
