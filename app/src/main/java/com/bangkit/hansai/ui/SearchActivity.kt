package com.bangkit.hansai.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.databinding.ActivitySearchBinding
import com.bangkit.hansai.ui.recipes.RecipesAdapter
import com.bangkit.hansai.ui.recipes.ViewRecipeActivity
import com.google.android.material.search.SearchView
import java.util.Date

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.searchView.show()

        val recipesAdapter = RecipesAdapter()
        binding.rvResultRecipes.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = recipesAdapter
        }

        recipesAdapter.apply {
            submitList(dummyRecipes)
            setOnItemClickCallback(object : RecipesAdapter.OnItemClickCallback {
                override fun onItemClicked(recipe: RecipeEntity) {
                    val intent = Intent(this@SearchActivity, ViewRecipeActivity::class.java)
                    intent.putExtra(ViewRecipeActivity.EXTRA_RECIPE, recipe)
                    startActivity(intent)
                }
            })
        }
    }

    companion object {
        private val dummyRecipes = listOf(
            RecipeEntity(
                id = "a",
                name = "Spaghetti Bolognese",
                ingredients = "Spaghetti, Ground Beef, Tomato Sauce, Onion, Garlic",
                instructions = "1. Cook spaghetti\n2. Prepare sauce\n3. Combine and serve",
                carbs = 75.0,
                protein = 20.0,
                fat = 15.0,
                calories = 2000.0
            ),
            RecipeEntity(
                id = "ab",
                name = "Chicken Salad",
                ingredients = "Chicken Breast, Lettuce, Tomato, Cucumber, Olive Oil",
                instructions = "1. Grill chicken\n2. Chop vegetables\n3. Mix and drizzle dressing",
                carbs = 10.0,
                protein = 25.0,
                fat = 8.0,
                calories = 2000.0
            ),
            RecipeEntity(
                id = "abc",
                name = "Pancakes",
                ingredients = "Flour, Eggs, Milk, Butter, Sugar",
                instructions = "1. Mix ingredients\n2. Cook on griddle\n3. Serve with syrup",
                carbs = 45.0,
                protein = 6.0,
                fat = 10.0,
                calories = 2000.0
            ),
            RecipeEntity(
                id = "abcd",
                name = "Vegetable Stir Fry",
                ingredients = "Broccoli, Carrot, Bell Pepper, Soy Sauce, Garlic",
                instructions = "1. Chop vegetables\n2. Stir-fry with sauce\n3. Serve hot",
                carbs = 35.0,
                protein = 5.0,
                fat = 3.0,
                calories = 2000.0
            ),
            RecipeEntity(
                id = "abcde",
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
}