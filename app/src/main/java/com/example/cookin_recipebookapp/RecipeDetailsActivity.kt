package com.example.cookin_recipebookapp

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.adapters.IngredientsAdapter
import com.example.cookin_recipebookapp.listeners.RecipeDetailsListener
import com.example.cookin_recipebookapp.models.RecipeDetailsResponse
import com.squareup.picasso.Picasso

class RecipeDetailsActivity : AppCompatActivity(), RecipeDetailsListener {

    private lateinit var textViewMealName: TextView
    private lateinit var textViewMealSource: TextView
    private lateinit var textViewMealSummary: TextView
    private lateinit var imageViewMealImage: ImageView
    private lateinit var recyclerViewMealIngredients: RecyclerView
    private lateinit var requestManager: RequestManager
    private var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        findViews()

        recipeId = intent.getStringExtra("id")?.toInt() ?: 0

        requestManager = RequestManager()
        requestManager.getRecipeDetails(getString(R.string.API_key), recipeId, this)
    }

    private fun findViews() {
        textViewMealName = findViewById(R.id.textView_meal_name)
        textViewMealSource = findViewById(R.id.textView_meal_source)
        textViewMealSummary = findViewById(R.id.textView_meal_summary)
        imageViewMealImage = findViewById(R.id.imageView_meal_image)
        recyclerViewMealIngredients = findViewById(R.id.recycler_meal_ingridients)
    }

    override fun didFetch(response: RecipeDetailsResponse, message: String) {
        textViewMealName.text = response.title
        textViewMealSource.text = response.sourceName
        textViewMealSummary.text = Html.fromHtml(response.summary)
        Picasso.get().load(response.image).into(imageViewMealImage)

        recyclerViewMealIngredients.setHasFixedSize(true)
        recyclerViewMealIngredients.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMealIngredients.adapter = IngredientsAdapter(this, response.extendedIngredients)
    }

    override fun didError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }
}
