package com.bangkit.hansai.ui.recipes

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.hansai.databinding.ActivityCreateRecipeBinding

class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                }
            }
        }
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