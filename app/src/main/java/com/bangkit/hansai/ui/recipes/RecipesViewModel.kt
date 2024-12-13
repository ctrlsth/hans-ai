package com.bangkit.hansai.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.data.remote.request.CreateRecipeRequest
import com.bangkit.hansai.data.remote.request.GenRecipeRequest
import com.bangkit.hansai.data.remote.response.GenRecipe
import com.bangkit.hansai.data.repository.RecipeRepository
import com.bangkit.hansai.data.repository.Result
import kotlinx.coroutines.launch

class RecipesViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _allRecipes = MutableLiveData<Result<List<RecipeEntity>>>()
    val allRecipes: LiveData<Result<List<RecipeEntity>>> get() = _allRecipes

    fun getRecipes(name: String? = null): LiveData<Result<List<RecipeEntity>>> {
        if (_allRecipes.value == null || _allRecipes.value is Result.Error) {
            val res = recipeRepository.getRecipes(name)
            _allRecipes.value = res.value
            return res
        } else {
            return _allRecipes
        }
    }

    fun searchRecipes(query: String) = recipeRepository.searchRecipes(query)

    fun createRecipe(
        name: String,
        ingredients: String,
        instructions: String,
        carbs: Double,
        protein: Double,
        fat: Double,
        calories: Double
    ) {
        viewModelScope.launch {
            val reqBody = CreateRecipeRequest(
                name = name,
                ingredients = ingredients,
                instructions = instructions,
                carbs = carbs,
                protein = protein,
                fat = fat,
                calories = calories
            )
            recipeRepository.createRecipe(reqBody)
        }
    }

    fun generateRecipe(ingredients: List<String>): LiveData<Result<GenRecipe>> {
        return recipeRepository.generateRecipe(GenRecipeRequest(ingredients))
    }
}