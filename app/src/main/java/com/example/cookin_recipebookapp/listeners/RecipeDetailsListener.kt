package com.example.cookin_recipebookapp.listeners

import com.example.cookin_recipebookapp.models.RecipeDetailsResponse

interface RecipeDetailsListener {
    fun didFetch(response: RecipeDetailsResponse, message: String)
    fun didError(message: String)
}
