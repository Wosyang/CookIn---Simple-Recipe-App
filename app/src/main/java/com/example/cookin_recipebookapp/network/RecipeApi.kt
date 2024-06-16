package com.example.cookin_recipebookapp.network

import com.example.cookin_recipebookapp.models.RecipeDetailsResponse
import com.example.cookin_recipebookapp.models.recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/random")
    fun callRandomRecipe(
        @Query("apiKey") apiKey: String,
        @Query("number") number: String,
        @Query("tags") tags: List<String>
    ): Call<recipes>

    @GET("recipes/{id}/information")
    fun callRecipeDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Call<RecipeDetailsResponse>
}
