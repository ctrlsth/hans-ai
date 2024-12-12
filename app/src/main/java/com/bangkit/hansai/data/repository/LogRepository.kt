package com.bangkit.hansai.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.data.local.room.dao.LogDao
import com.bangkit.hansai.data.remote.request.PredictCalorieRequest
import com.bangkit.hansai.data.remote.request.SaveLogRequest
import com.bangkit.hansai.data.remote.response.Log
import com.bangkit.hansai.data.remote.response.LogItems
import com.bangkit.hansai.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class LogRepository private constructor(
    private val apiService: ApiService,
    private val logDao: LogDao
) {

    fun getLogs(month: Int, year: Int): LiveData<Result<List<LogEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getLogs(month, year)
            if (response.data.isEmpty()) {
                emit(Result.Error("No data found"))
            } else {
                val logList = response.data.map { log ->
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    LogEntity(
                        id = log.id,
                        date = dateFormat.parse(log.date)!!,
                        title = log.logTitle,
                        summary = log.description,
                        breakfast = filterLogItemsByType(log.foodLogItems, "Breakfast"),
                        lunch = filterLogItemsByType(log.foodLogItems, "Lunch"),
                        dinner = filterLogItemsByType(log.foodLogItems, "Dinner"),
                        totalCalories = log.totalCalories,
                        totalProtein = log.totalProtein,
                        totalCarbs = log.totalCarbs,
                        totalFat = log.totalFat,
                        baseGoal = log.baseGOal,
                        weight = log.weight
                    )
                }
                withContext(Dispatchers.IO) {
                    logDao.insert(logList)
                }
                liveData {
                    emit(Result.Success(response.data))
                }
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

    private fun filterLogItemsByType(logItems: List<LogItems>, type: String): List<String> {
        return logItems.filter { it.type == type }
            .map { it.recipeId }
    }

    companion object {
        @Volatile
        private var instance: LogRepository? = null
        fun getInstance(
            apiService: ApiService,
            logDao: LogDao
        ): LogRepository =
            instance ?: synchronized(this) {
                instance ?: LogRepository(apiService, logDao)
            }.also { instance = it }
    }
}