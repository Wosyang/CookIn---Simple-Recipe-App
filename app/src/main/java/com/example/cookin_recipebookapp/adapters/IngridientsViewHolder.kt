package com.example.cookin_recipebookapp.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.R

class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewIngredientName: TextView = itemView.findViewById(R.id.textView_ingridients_name)
    val textViewIngredientQuantity: TextView = itemView.findViewById(R.id.textView_ingridients_quantity)
    val imageViewIngredient: ImageView = itemView.findViewById(R.id.imageView_ingridients)
}
