package com.bangkit.hansai.ui.recipes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.preferences.UserPreferences
import com.bangkit.hansai.data.local.preferences.dataStore
import com.bangkit.hansai.data.remote.response.GenRecipe
import com.bangkit.hansai.data.repository.Result
import com.bangkit.hansai.databinding.ActivityCreateRecipeBinding
import com.bangkit.hansai.ui.factory.RecipeViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: RecipeViewModelFactory = RecipeViewModelFactory.getInstance(this)
        val recipesViewModel: RecipesViewModel by viewModels {
            factory
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root as View) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val content = binding.content
        content.saveButton.setOnClickListener {
            when ("") {
                content.recipeTitle.text.toString() -> {
                    content.recipeTitle.error = "Title cannot be empty"
                }

                content.ingredients.text.toString() -> {
                    content.ingredients.error = "Ingredients cannot be empty"
                }

                content.stepByStep.text.toString() -> {
                    content.stepByStep.error = "Step by step cannot be empty"
                }

                content.inputCarbs.text.toString() -> {
                    content.inputCarbs.error = "Carbs cannot be empty"
                }

                content.inputProtein.text.toString() -> {
                    content.inputProtein.error = "Protein cannot be empty"
                }

                content.inputFat.text.toString() -> {
                    content.inputFat.error = "Fat cannot be empty"
                }

                else -> {
                    // Use a coroutine scope to safely call the suspend function
                    lifecycleScope.launch {
                        val userPreference = UserPreferences.getInstance(application.dataStore)
                        val currentCalorie = userPreference.getUserCurrentCalorie().first()

                        try {
                            recipesViewModel.createRecipe(
                                content.recipeTitle.text.toString(),
                                content.ingredients.text.toString(),
                                content.stepByStep.text.toString(),
                                content.inputCarbs.text.toString().toDouble(),
                                content.inputProtein.text.toString().toDouble(),
                                content.inputFat.text.toString().toDouble(),
                                currentCalorie
                            )
                            finish()
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@CreateRecipeActivity,
                                "Create Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.extendedFab.setOnClickListener {
            showGenerateRecipeDialog(recipesViewModel)
        }
    }

    private fun showGenerateRecipeDialog(recipesViewModel: RecipesViewModel) {
        val dialog = BottomSheetDialog(this)
        val dialogView = layoutInflater.inflate(R.layout.layout_dialog, binding.root, false)
        dialog.setContentView(dialogView)

        val ingredients = dialogView.findViewById<EditText>(R.id.ingredients)
        val saveButton = dialogView.findViewById<Button>(R.id.saveRecipeButton)

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(ingredients.windowToken, 0)

        saveButton.setOnClickListener {
            val ingredientList = ingredients.text.toString()

            if (ingredientList.isEmpty()) {
                ingredients.error = "Ingredients cannot be empty"
                return@setOnClickListener
            }

            val listIngredients = ingredientList
                .split(",")
                .map { it.trim() }
            try {
                recipesViewModel.generateRecipe(listIngredients).observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            Log.d("RecipesViewModel", "Loading...")
                        }

                        is Result.Success -> {
                            val recipe = result.data
                            populateInputs(recipe)
                            ingredients.text.clear()
                            dialog.dismiss()
                        }

                        is Result.Error -> {
                            Toast.makeText(this, "Generate Error", Toast.LENGTH_SHORT).show()
                            Log.d("RecipesViewModel", "Error: ${result.error}")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("RecipesViewModel", "Error: ${e.message}")
            }
        }
        dialog.show()
    }

    private fun populateInputs(recipe: GenRecipe) {
        val content = binding.content
        content.recipeTitle.setText(recipe.title)
        content.ingredients.setText(recipe.bahan)
        content.stepByStep.setText(recipe.langkah)
        content.inputCarbs.setText(
            String.format(
                Locale.getDefault(),
                "%.2f",
                recipe.karbohidrat
            )
        )
        content.inputProtein.setText(
            String.format(
                Locale.getDefault(),
                "%.2f",
                recipe.protein
            )
        )
        content.inputFat.setText(
            String.format(
                Locale.getDefault(),
                "%.2f",
                recipe.lemak
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}