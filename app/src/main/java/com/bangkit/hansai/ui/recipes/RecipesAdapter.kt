package com.bangkit.hansai.ui.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.databinding.ItemRecipeBinding
import java.util.Locale

class RecipesAdapter : ListAdapter<RecipeEntity, RecipesAdapter.RecipesViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class RecipesViewHolder(
        val binding: ItemRecipeBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            // set up view here
            binding.recipeTitle.text = recipe.name
            val totalCalories =
                recipe.protein * 4 + recipe.carbs * 4 + recipe.fat * 9
            binding.calories.text = String.format(Locale.getDefault(), "%.1f kcal", totalCalories)
            binding.actionButton.setOnClickListener { view ->
                val popupMenu = PopupMenu(view.context, binding.actionButton)
                popupMenu.inflate(R.menu.rv_item_menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.edit -> {
                            // Handle edit action
                            true
                        }

                        R.id.delete -> {
                            // Handle delete action
                            true
                        }

                        else -> false
                    }
                }

                popupMenu.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
        Log.d("RecipesAdapter", "Binding recipe: $recipe")
        holder.bind(recipe)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(recipe) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(recipe: RecipeEntity)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeEntity>() {
            override fun areItemsTheSame(
                oldItem: RecipeEntity,
                newItem: RecipeEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RecipeEntity,
                newItem: RecipeEntity
            ): Boolean {
                return oldItem.ingredients == newItem.ingredients
            }
        }
    }
}