package com.bangkit.hansai.ui.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.data.local.entity.RecipeEntity
import java.util.Date

class TrackViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    val dummy = generateDummyLogEntity()
    val dummyRecipes = listOf(
        RecipeEntity(
            id = 1,
            title = "Spaghetti Bolognese",
            ingredients = "Spaghetti, Ground Beef, Tomato Sauce, Onion, Garlic",
            steps = "1. Cook spaghetti\n2. Prepare sauce\n3. Combine and serve",
            carbs = 75.0,
            protein = 20.0,
            fat = 15.0,
            lastUpdate = Date(),
            calories = 2000.0
        ),
        RecipeEntity(
            id = 2,
            title = "Chicken Salad",
            ingredients = "Chicken Breast, Lettuce, Tomato, Cucumber, Olive Oil",
            steps = "1. Grill chicken\n2. Chop vegetables\n3. Mix and drizzle dressing",
            carbs = 10.0,
            protein = 25.0,
            fat = 8.0,
            lastUpdate = Date(),
            calories = 2000.0
        ),
        RecipeEntity(
            id = 3,
            title = "Pancakes",
            ingredients = "Flour, Eggs, Milk, Butter, Sugar",
            steps = "1. Mix ingredients\n2. Cook on griddle\n3. Serve with syrup",
            carbs = 45.0,
            protein = 6.0,
            fat = 10.0,
            lastUpdate = Date(),
            calories = 2000.0
        ),
        RecipeEntity(
            id = 4,
            title = "Vegetable Stir Fry",
            ingredients = "Broccoli, Carrot, Bell Pepper, Soy Sauce, Garlic",
            steps = "1. Chop vegetables\n2. Stir-fry with sauce\n3. Serve hot",
            carbs = 35.0,
            protein = 5.0,
            fat = 3.0,
            lastUpdate = Date(),
            calories = 2000.0
        ),
        RecipeEntity(
            id = 5,
            title = "Beef Burger",
            ingredients = "Ground Beef, Bun, Lettuce, Tomato, Cheese",
            steps = "1. Form patty and grill\n2. Assemble burger\n3. Serve with fries",
            carbs = 40.0,
            protein = 30.0,
            fat = 25.0,
            lastUpdate = Date(),
            calories = 2000.0
        )
    )

    private fun generateDummyLogEntity(): List<LogEntity> {
        val dummyLogs = mutableListOf<LogEntity>()

        val breakfast1 = listOf(1, 2, 3) // Meal IDs for breakfast
        val lunch1 = listOf(4, 5)       // Meal IDs for lunch
        val dinner1 = listOf(1, 3)     // Meal IDs for dinner

        dummyLogs.add(
            LogEntity(
                id = 1,
                title = "Healthy Monday",
                summary = "Focused on healthy meals with balanced nutrition.",
                date = Date(),
                baseGoal = 2000.0,
                currentWeight = 105.4,
                breakfast = breakfast1,
                lunch = lunch1,
                dinner = dinner1,
                calories = 1800.0,
                carbs = 200.0,
                protein = 120.0,
                fat = 60.0,
                lastUpdate = Date()
            )
        )

        val breakfast2 = listOf(2, 4)      // Meal IDs for breakfast
        val lunch2 = listOf(3, 5)         // Meal IDs for lunch
        val dinner2 = listOf(1, 2)       // Meal IDs for dinner

        dummyLogs.add(
            LogEntity(
                id = 2,
                title = "Cheat Day",
                summary = "A day with some indulgent meals.",
                date = Date(System.currentTimeMillis() - 86400000), // Yesterday's date
                breakfast = breakfast2,
                lunch = lunch2,
                dinner = dinner2,
                baseGoal = 2000.0,
                currentWeight = 105.4,
                calories = 2500.0,
                carbs = 300.0,
                protein = 100.0,
                fat = 120.0,
                lastUpdate = Date(System.currentTimeMillis() - 43200000) // Half a day ago
            )
        )

        return dummyLogs
    }
}