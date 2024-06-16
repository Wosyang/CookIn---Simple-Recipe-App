package com.example.cookin_recipebookapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.R
import com.example.cookin_recipebookapp.models.ExtendedIngredientX
import com.squareup.picasso.Picasso

class IngredientsAdapter(
    private val context: Context,
    private val ingredients: List<ExtendedIngredientX>
) : RecyclerView.Adapter<IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_meal_ingridients, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.textViewIngredientName.text = ingredient.name
        holder.textViewIngredientQuantity.text = "${ingredient.amount} ${ingredient.unit}"
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}").into(holder.imageViewIngredient)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}
