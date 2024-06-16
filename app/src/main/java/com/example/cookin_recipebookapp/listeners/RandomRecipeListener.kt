package com.example.cookin_recipebookapp.listeners

import com.example.cookin_recipebookapp.models.recipes

interface RandomRecipeListener {
    fun didFetch(response: recipes, message: String)
    fun didError(message: String)
}
