package com.example.food1.domain.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("results")
    val result: List<ResultX>
)