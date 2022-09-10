package com.example.food1.domain.repository

import com.example.food1.domain.model.Result
import com.example.food1.domain.model.ResultX
import com.example.food1.util.Resource
import kotlinx.coroutines.flow.Flow

interface FoodApiRepository {
    fun getRecipes(queries: Map<String, String>): Flow<Resource<Result>>
}