package com.bangkit.hansai.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.data.repository.Result
import com.bangkit.hansai.databinding.ActivitySearchBinding
import com.bangkit.hansai.ui.factory.RecipeViewModelFactory
import com.bangkit.hansai.ui.recipes.RecipesAdapter
import com.bangkit.hansai.ui.recipes.RecipesViewModel
import com.bangkit.hansai.ui.recipes.ViewRecipeActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: RecipeViewModelFactory = RecipeViewModelFactory.getInstance(this)
        val recipesViewModel: RecipesViewModel by viewModels {
            factory
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recipesAdapter = RecipesAdapter()
        binding.rvResultRecipes.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = recipesAdapter
        }

        recipesViewModel.getRecipes().observe(this@SearchActivity) { result ->
            Log.d("RecipesViewModel", "Result: $result")
            when (result) {
                is Result.Loading -> {
                    Log.d("RecipesViewModel", "Loading...")
                }

                is Result.Success -> {
                    recipesAdapter.submitList(result.data)
                    Log.d("RecipesViewModel", "Data submitted to adapter: ${result.data}")
                }

                is Result.Error -> {
                    Log.d("RecipesViewModel", "Error: ${result.error}")
                }
            }
        }

        recipesAdapter.apply {
            setOnItemClickCallback(object : RecipesAdapter.OnItemClickCallback {
                override fun onItemClicked(recipe: RecipeEntity) {
                    val intent = Intent(this@SearchActivity, ViewRecipeActivity::class.java)
                    intent.putExtra(ViewRecipeActivity.EXTRA_RECIPE, recipe)
                    startActivity(intent)
                }
            })
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recipesViewModel.searchRecipes(query ?: "").observe(this@SearchActivity) {
                    recipesAdapter.submitList(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipesViewModel.searchRecipes(newText ?: "").observe(this@SearchActivity) {
                    recipesAdapter.submitList(it)
                }
                return true
            }
        })

    }
}