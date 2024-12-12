package com.bangkit.hansai.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredictResponse(

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean,

    @field:SerializedName("messages")
    val messages: List<String>,

    @field:SerializedName("prediction")
    val prediction: Double? = null
) : Parcelable
