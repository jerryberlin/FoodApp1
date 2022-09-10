package com.example.food1.presentation.screen.detail

import com.example.food1.domain.model.ResultX

data class FoodDetailState(
    val isLoading: Boolean = false,
    val recipe: ResultX? = null,
    val error: String = ""
)