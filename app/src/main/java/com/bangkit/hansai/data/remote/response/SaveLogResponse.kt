package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SaveLogResponse(

    @field:SerializedName("data")
    val data: Status? = null,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean
) : Parcelable

@Parcelize
data class Status(

    @field:SerializedName("data")
    val data: Log? = null,

    @field:SerializedName("success")
    val success: Boolean,
) : Parcelable
