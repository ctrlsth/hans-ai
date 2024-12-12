package com.bangkit.hansai.ui.recipes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.databinding.ActivityCreateRecipeBinding
import com.bangkit.hansai.ui.track.CreateLogActivity
import java.util.Locale


class ViewRecipeActivity : AppCompatActivity() {
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
        binding.topAppBar.title = getString(R.string.title_recipes)

        val recipe = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_RECIPE, RecipeEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_RECIPE)
        }

        binding.extendedFab.hide()
        val content = binding.content

        if (recipe != null) {
            disableAndSetEditText(content.recipeTitle, recipe.title)
            disableAndSetEditText(
                content.ingredients,
                recipe.ingredients.split(",").joinToString("\n") { "    • ${it.trim()}" })
            disableAndSetEditText(
                content.stepByStep,
                recipe.steps.split("\n").joinToString("\n") { "    ${it.trim()}" })
            disableAndSetEditText(content.inputCarbs, recipe.carbs.toString())
            disableAndSetEditText(content.inputProtein, recipe.protein.toString())
            disableAndSetEditText(content.inputFat, recipe.fat.toString())

            val totalCalories =
                recipe.protein * 4 + recipe.carbs * 4 + recipe.fat * 9
            content.totalCalorie.text =
                String.format(Locale.getDefault(), "%.1f kcal", totalCalories)

            content.saveButton.apply {
                text = getString(R.string.redirect_button)
                setOnClickListener {
                    val intent = Intent(this@ViewRecipeActivity, CreateLogActivity::class.java)
                    startActivity(intent)
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

    private fun disableAndSetEditText(editText: EditText, newText: String) {
        editText.apply {
            setText(newText)
            setTextColor(ContextCompat.getColor(context, R.color.md_theme_onSurface))
            isFocusable = false
            isEnabled = false
            isCursorVisible = false
        }
    }

    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }
}