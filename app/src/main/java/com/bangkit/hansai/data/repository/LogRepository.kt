package com.bangkit.hansai.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.hansai.data.remote.request.PredictCalorieRequest
import com.bangkit.hansai.data.remote.request.SaveLogRequest
import com.bangkit.hansai.data.remote.response.Log
import com.bangkit.hansai.data.remote.retrofit.ApiService

class LogRepository private constructor(
    private val apiService: ApiService,
) {

    fun getLogs(month: Int, year: Int): LiveData<Result<List<Log>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getLogs(month, year)
            if (response.data.isEmpty()) {
                emit(Result.Error("No data found"))
            } else {
                emit(Result.Success(response.data))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getLogById(logId: String): LiveData<Result<Log>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getLogById(logId)
            if (response.data.isEmpty()) {
                emit(Result.Error("No data found"))
            } else {
                emit(Result.Success(response.data[0]))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun saveLog(reqBody: SaveLogRequest): LiveData<Result<String>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.saveLog(reqBody)
            if (response.isSuccess && response.data?.success == true) {
                emit(Result.Success(response.data.data?.id ?: ""))
            } else {
                emit(Result.Error(response.messages.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getCalorieIntake(reqBody: PredictCalorieRequest): LiveData<Result<Double>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getCalorieIntake(reqBody)
                if (response.isSuccess) {
                    emit(Result.Success(response.prediction!!))
                } else {
                    emit(Result.Error(response.messages.toString()))
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: LogRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): LogRepository =
            instance ?: synchronized(this) {
                instance ?: LogRepository(apiService)
            }.also { instance = it }
    }
}