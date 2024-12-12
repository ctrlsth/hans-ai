package com.bangkit.hansai.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.data.repository.Result
import com.bangkit.hansai.databinding.FragmentRecipesBinding
import com.bangkit.hansai.ui.SearchActivity
import com.bangkit.hansai.ui.factory.RecipeViewModelFactory
import com.bangkit.hansai.ui.factory.UserViewModelFactory
import com.bangkit.hansai.ui.home.HomeViewModel

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: RecipeViewModelFactory = RecipeViewModelFactory.getInstance(requireActivity())
        val recipesViewModel: RecipesViewModel by viewModels {
            factory
        }

        val recipesAdapter = RecipesAdapter()
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipesAdapter
        }

        recipesAdapter.apply {
            setOnItemClickCallback(object : RecipesAdapter.OnItemClickCallback {
                override fun onItemClicked(recipe: RecipeEntity) {
                    val intent = Intent(requireContext(), ViewRecipeActivity::class.java)
                    intent.putExtra(ViewRecipeActivity.EXTRA_RECIPE, recipe)
                    startActivity(intent)
                }
            })
        }

        recipesViewModel.getRecipes().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    Log.d("RecipesViewModel", "Loading...")
                }

                is Result.Success -> {
                    recipesAdapter.submitList(result.data)
                }

                is Result.Error -> {
                    Log.d("RecipesViewModel", "Error: ${result.error}")
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), CreateRecipeActivity::class.java)
            startActivity(intent)
        }

        binding.searchBar.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}