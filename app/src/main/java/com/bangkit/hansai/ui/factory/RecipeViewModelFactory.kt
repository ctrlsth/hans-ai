package com.bangkit.hansai.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.hansai.data.repository.RecipeRepository
import com.bangkit.hansai.di.Injection
import com.bangkit.hansai.ui.recipes.RecipesViewModel

class RecipeViewModelFactory private constructor(private val recipeRepository: RecipeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            return RecipesViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: RecipeViewModelFactory? = null
        fun getInstance(context: Context): RecipeViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: RecipeViewModelFactory(Injection.provideRecipeRepository(context))
            }.also { instance = it }
    }
}