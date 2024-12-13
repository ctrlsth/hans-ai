package com.bangkit.hansai.ui.track

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.databinding.ActivityCreateLogBinding
import com.bangkit.hansai.ui.MainActivity
import com.bangkit.hansai.ui.factory.LogViewModelFactory
import com.bangkit.hansai.ui.recipes.RecipesAdapter
import com.bangkit.hansai.ui.recipes.ViewRecipeActivity
import java.text.SimpleDateFormat
import java.util.Locale

class CreateLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory: LogViewModelFactory = LogViewModelFactory.getInstance(this@CreateLogActivity)
        val trackViewModel: TrackViewModel by viewModels {
            factory
        }

        binding = ActivityCreateLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root as View) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.topAppBar.title = "Your Log"

        val log = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_LOG, LogEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_LOG)
        }

        val content = binding.content

        if (log != null) {
            // Set date, title, and summary
            content.logDate.text =
                SimpleDateFormat("EEE, dd MM yyyy", Locale.getDefault()).format(log.date)
            content.logTitle.setText(log.title)
            content.logSummary.setText(log.summary)
        }

        // Set up RecyclerView adapters for breakfast, lunch, and dinner
        val breakfastAdapter = RecipesAdapter()
        content.rvBreakfast.apply {
            layoutManager = LinearLayoutManager(this@CreateLogActivity)
            adapter = breakfastAdapter
        }

        val lunchAdapter = RecipesAdapter()
        content.rvLunch.apply {
            layoutManager = LinearLayoutManager(this@CreateLogActivity)
            adapter = lunchAdapter
        }

        val dinnerAdapter = RecipesAdapter()
        content.rvDinner.apply {
            layoutManager = LinearLayoutManager(this@CreateLogActivity)
            adapter = dinnerAdapter
        }

        Log.d("CreateLogActivity", log.toString())
        /*
        if (log != null) {
            setupAdapter(breakfastAdapter, trackViewModel.dummyRecipes, log.breakfast)
            setupAdapter(lunchAdapter, trackViewModel.dummyRecipes, log.lunch)
            setupAdapter(dinnerAdapter, trackViewModel.dummyRecipes, log.dinner)
        }
         */

        content.addMealButtonBreakfast.cardAddMeal.setOnClickListener {
            // Open Search & Select Recipe Activity
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@CreateLogActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupAdapter(
        adapter: RecipesAdapter,
        recipes: List<RecipeEntity>,
        logCategory: List<Int>
    ) {
        adapter.apply {
//            submitList(recipes.filter { it.id in logCategory })
            setOnItemClickCallback(object : RecipesAdapter.OnItemClickCallback {
                override fun onItemClicked(recipe: RecipeEntity) {
                    val intent = Intent(this@CreateLogActivity, ViewRecipeActivity::class.java)
                    intent.putExtra(ViewRecipeActivity.EXTRA_RECIPE, recipe)
                    startActivity(intent)
                }
            })
        }
    }

    companion object {
        const val EXTRA_LOG = "extra_log"
    }
}
