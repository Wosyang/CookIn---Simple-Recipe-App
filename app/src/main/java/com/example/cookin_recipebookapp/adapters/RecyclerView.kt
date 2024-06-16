package com.example.cookin_recipebookapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.R
import com.example.cookin_recipebookapp.listeners.RecipeClickListener
import com.example.cookin_recipebookapp.models.Recipe
import com.squareup.picasso.Picasso

class RandomRecipeAdapter(
    private val context: Context,
    private var recipes: List<Recipe>,
    private val listener: RecipeClickListener
) : RecyclerView.Adapter<RandomRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false)
        return RandomRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.textViewTitle.text = recipe.title
        holder.textViewServings.text = "Servings: ${recipe.servings}"
        holder.textViewLikes.text = "Likes: ${recipe.aggregateLikes}"
        holder.textViewTime.text = "Time: ${recipe.readyInMinutes} mins"
        Picasso.get().load(recipe.image).into(holder.imageViewFood)

        holder.randomListContainer.setOnClickListener {
            listener.onRecipeClicked(recipe.id.toString()) // Ensure recipe has an id property
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
