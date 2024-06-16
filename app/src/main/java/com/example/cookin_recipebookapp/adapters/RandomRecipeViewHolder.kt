package com.example.cookin_recipebookapp.adapters// com.example.cookin_recipebookapp.adapters.RandomRecipeViewHolder.kt
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookin_recipebookapp.R

class RandomRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val randomListContainer: CardView = itemView.findViewById(R.id.random_list_container)
    val textViewTitle: TextView = itemView.findViewById(R.id.textview_title)
    val textViewServings: TextView = itemView.findViewById(R.id.textView_serving)
    val textViewLikes: TextView = itemView.findViewById(R.id.textView_likes)
    val textViewTime: TextView = itemView.findViewById(R.id.textView_time)
    val imageViewFood: ImageView = itemView.findViewById(R.id.ImageView_food)
}
