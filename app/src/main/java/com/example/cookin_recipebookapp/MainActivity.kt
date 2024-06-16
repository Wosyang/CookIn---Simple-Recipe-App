package com.example.cookin_recipebookapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.adapters.RandomRecipeAdapter
import com.example.cookin_recipebookapp.listeners.RandomRecipeListener
import com.example.cookin_recipebookapp.listeners.RecipeClickListener
import com.example.cookin_recipebookapp.models.recipes

class MainActivity : AppCompatActivity(), RecipeClickListener, RandomRecipeListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var randomRecipeAdapter: RandomRecipeAdapter
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView
    private lateinit var requestManager: RequestManager
    private var tags: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycler_random)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Setup Spinner
        spinner = findViewById(R.id.spinner_tags)
        val arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tags,
            R.layout.spinner_text
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner.adapter = arrayAdapter

        // Initialize RequestManager
        requestManager = RequestManager()

        // Setup SearchView
        searchView = findViewById(R.id.searchView_home)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    tags = query
                    fetchRandomRecipe()
                } else {
                    Toast.makeText(this@MainActivity, "Please enter a tag to search", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // Set OnItemSelectedListener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tags = parent.getItemAtPosition(position).toString()
                fetchRandomRecipe()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun fetchRandomRecipe() {
        val apiKey = getString(R.string.API_key)
        val number = "10"  // Number of recipes to fetch

        // Format the tags as a comma-separated string
        val formattedTags = tags.split(",").joinToString(",") { it.trim() }

        // Log the tags being sent
        Log.d("MainActivity", "Fetching recipes with tags: $formattedTags")

        requestManager.getRandomRecipes(apiKey, number, formattedTags.split(","), this)
    }

    override fun didFetch(response: recipes, message: String) {
        randomRecipeAdapter = RandomRecipeAdapter(this, response.recipes, this)
        recyclerView.adapter = randomRecipeAdapter
    }

    override fun didError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }

    override fun onRecipeClicked(id: String) {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
