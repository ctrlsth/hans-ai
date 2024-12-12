package com.bangkit.hansai.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.hansai.data.local.entity.RecipeEntity
import java.util.Date

class RecipesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val dummyRecipes = listOf(
        RecipeEntity(
            id = 1,
            name = "Spaghetti Bolognese",
            ingredients = "Spaghetti, Ground Beef, Tomato Sauce, Onion, Garlic",
            instructions = "1. Cook spaghetti\n2. Prepare sauce\n3. Combine and serve",
            carbs = 75.0,
            protein = 20.0,
            fat = 15.0,
            calories = 2000.0
        ),
        RecipeEntity(
            id = 2,
            name = "Chicken Salad",
            ingredients = "Chicken Breast, Lettuce, Tomato, Cucumber, Olive Oil",
            instructions = "1. Grill chicken\n2. Chop vegetables\n3. Mix and drizzle dressing",
            carbs = 10.0,
            protein = 25.0,
            fat = 8.0,
            calories = 2000.0
        ),
        RecipeEntity(
            id = 3,
            name = "Pancakes",
            ingredients = "Flour, Eggs, Milk, Butter, Sugar",
            instructions = "1. Mix ingredients\n2. Cook on griddle\n3. Serve with syrup",
            carbs = 45.0,
            protein = 6.0,
            fat = 10.0,
            calories = 2000.0
        ),
        RecipeEntity(
            id = 4,
            name = "Vegetable Stir Fry",
            ingredients = "Broccoli, Carrot, Bell Pepper, Soy Sauce, Garlic",
            instructions = "1. Chop vegetables\n2. Stir-fry with sauce\n3. Serve hot",
            carbs = 35.0,
            protein = 5.0,
            fat = 3.0,
            calories = 2000.0
        ),
        RecipeEntity(
            id = 5,
            name = "Beef Burger",
            ingredients = "Ground Beef, Bun, Lettuce, Tomato, Cheese",
            instructions = "1. Form patty and grill\n2. Assemble burger\n3. Serve with fries",
            carbs = 40.0,
            protein = 30.0,
            fat = 25.0,
            calories = 2000.0
        )
    )
}