package com.example.food1.data.remote

import com.example.food1.domain.model.Result
import com.example.food1.domain.model.ResultX
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Result



}