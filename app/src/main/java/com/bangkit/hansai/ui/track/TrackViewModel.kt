package com.bangkit.hansai.ui.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.data.remote.request.FoodItem
import com.bangkit.hansai.data.remote.request.SaveLogRequest
import com.bangkit.hansai.data.repository.LogRepository
import com.bangkit.hansai.data.repository.Result
import kotlinx.coroutines.launch

class TrackViewModel(private val logRepository: LogRepository) : ViewModel() {

    fun getLogs(month: Int, year: Int): LiveData<Result<List<LogEntity>>> {
        return logRepository.getLogs(month, year)
    }

    fun saveLog(
        title: String,
        summary: String,
        breakfast: List<String>,
        lunch: List<String>,
        dinner: List<String>,
        totalCalories: Double,
        totalProtein: Double,
        totalCarbs: Double,
        totalFat: Double,
        baseGoal: Double,
        weight: Double
    ) {
        viewModelScope.launch {
            val reqBody = SaveLogRequest(
                logTitle = title,
                description = summary,
                totalCarbs = totalCarbs,
                totalCalories = totalCalories,
                totalFat = totalFat,
                totalProtein = totalProtein,
                baseGoal = baseGoal,
                weight = weight,
                foodItems = mapToFoodItems(breakfast, "Breakfast") + mapToFoodItems(
                    lunch,
                    "Lunch"
                ) + mapToFoodItems(dinner, "Dinner")
            )
            logRepository.saveLog(reqBody)
        }
    }


    fun mapToFoodItems(recipes: List<String>, type: String): List<FoodItem> {
        return recipes.map { recipeId -> FoodItem(recipeId, type) }
    }
}