package com.example.food1.presentation.screen.home

import com.example.food1.domain.model.Result
import com.example.food1.domain.model.ResultX

data class FoodListState(
    val isLoading: Boolean = false,
    val recipes: Result = Result(emptyList()),
    val error: String = ""
)