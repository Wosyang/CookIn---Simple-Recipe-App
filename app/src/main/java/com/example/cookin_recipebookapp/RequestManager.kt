package com.example.cookin_recipebookapp

import com.example.cookin_recipebookapp.listeners.RandomRecipeListener
import com.example.cookin_recipebookapp.listeners.RecipeDetailsListener
import com.example.cookin_recipebookapp.models.RecipeDetailsResponse
import com.example.cookin_recipebookapp.models.recipes
import com.example.cookin_recipebookapp.network.RecipeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestManager {

    private val recipeApi: RecipeApi

    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        recipeApi = retrofit.create(RecipeApi::class.java)
    }

    fun getRandomRecipes(apiKey: String, number: String, tags: List<String>, listener: RandomRecipeListener) {
        val call = recipeApi.callRandomRecipe(apiKey, number, tags)
        call.enqueue(object : Callback<recipes> {
            override fun onResponse(call: Call<recipes>, response: Response<recipes>) {
                if (response.isSuccessful) {
                    listener.didFetch(response.body()!!, response.message())
                } else {
                    listener.didError(response.message())
                }
            }

            override fun onFailure(call: Call<recipes>, t: Throwable) {
                listener.didError(t.message ?: "Unknown error")
            }
        })
    }

    fun getRecipeDetails(apiKey: String, id: Int, listener: RecipeDetailsListener) {
        val call = recipeApi.callRecipeDetails(id, apiKey)
        call.enqueue(object : Callback<RecipeDetailsResponse> {
            override fun onResponse(call: Call<RecipeDetailsResponse>, response: Response<RecipeDetailsResponse>) {
                if (response.isSuccessful) {
                    listener.didFetch(response.body()!!, response.message())
                } else {
                    listener.didError(response.message())
                }
            }

            override fun onFailure(call: Call<RecipeDetailsResponse>, t: Throwable) {
                listener.didError(t.message ?: "Unknown error")
            }
        })
    }
}
