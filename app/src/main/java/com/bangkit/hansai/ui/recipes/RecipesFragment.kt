package com.bangkit.hansai.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipesViewModel =
            ViewModelProvider(this)[RecipesViewModel::class.java]

        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recipesAdapter = RecipesAdapter()
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipesAdapter
        }

        recipesAdapter.apply {
            submitList(recipesViewModel.dummyRecipes)
            setOnItemClickCallback(object : RecipesAdapter.OnItemClickCallback {
                override fun onItemClicked(recipe: RecipeEntity) {
                    val intent = Intent(requireContext(), ViewRecipeActivity::class.java)
                    intent.putExtra(ViewRecipeActivity.EXTRA_RECIPE, recipe)
                    startActivity(intent)
                }
            })
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), CreateRecipeActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}