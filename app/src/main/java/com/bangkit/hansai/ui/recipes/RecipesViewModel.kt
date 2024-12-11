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
            title = "Spaghetti Bolognese",
            description = "A classic Italian pasta dish with a rich meat sauce.",
            ingredients = "Spaghetti, Ground Beef, Tomato Sauce, Onion, Garlic",
            steps = "1. Cook spaghetti\n2. Prepare sauce\n3. Combine and serve",
            carbs = 75,
            protein = 20,
            fat = 15,
            duration = 30,
            date = Date(),
            lastUpdate = Date()
        ),
        RecipeEntity(
            id = 2,
            title = "Chicken Salad",
            description = "A healthy salad with grilled chicken and fresh vegetables.",
            ingredients = "Chicken Breast, Lettuce, Tomato, Cucumber, Olive Oil",
            steps = "1. Grill chicken\n2. Chop vegetables\n3. Mix and drizzle dressing",
            carbs = 10,
            protein = 25,
            fat = 8,
            duration = 20,
            date = Date(),
            lastUpdate = Date()
        ),
        RecipeEntity(
            id = 3,
            title = "Pancakes",
            description = "Fluffy pancakes perfect for breakfast or brunch.",
            ingredients = "Flour, Eggs, Milk, Butter, Sugar",
            steps = "1. Mix ingredients\n2. Cook on griddle\n3. Serve with syrup",
            carbs = 45,
            protein = 6,
            fat = 10,
            duration = 15,
            date = Date(),
            lastUpdate = Date()
        ),
        RecipeEntity(
            id = 4,
            title = "Vegetable Stir Fry",
            description = "A quick and easy stir-fry with fresh vegetables.",
            ingredients = "Broccoli, Carrot, Bell Pepper, Soy Sauce, Garlic",
            steps = "1. Chop vegetables\n2. Stir-fry with sauce\n3. Serve hot",
            carbs = 35,
            protein = 5,
            fat = 3,
            duration = 15,
            date = Date(),
            lastUpdate = Date()
        ),
        RecipeEntity(
            id = 5,
            title = "Beef Burger",
            description = "A juicy beef burger with lettuce, tomato, and cheese.",
            ingredients = "Ground Beef, Bun, Lettuce, Tomato, Cheese",
            steps = "1. Form patty and grill\n2. Assemble burger\n3. Serve with fries",
            carbs = 40,
            protein = 30,
            fat = 25,
            duration = 25,
            date = Date(),
            lastUpdate = Date()
        )
    )
}