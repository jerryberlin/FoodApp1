package com.example.food1.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food1.domain.model.Result
import com.example.food1.domain.repository.FoodApiRepository
import com.example.food1.util.Constant.API_KEY
import com.example.food1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodApiRepository: FoodApiRepository
): ViewModel(){

    private val _state = MutableStateFlow(FoodListState())
    val state: StateFlow<FoodListState> = _state.asStateFlow()

//    private val _state = MutableStateFlow(FoodListState())
//    val state: StateFlow<FoodListState> = _state

    init {
        getRecipes(applyQueries())
    }

    private fun getRecipes(queries: Map<String, String>) {
        foodApiRepository.getRecipes(queries = queries).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = FoodListState(recipes = result.data ?: Result(emptyList()))
                }
                is Resource.Error -> {
                    _state.value =
                        FoodListState(
                            error = result.message ?: "An un expected error occurred"
                        )
                    Log.d("TAG", "getFoodRecipes: ERROR")
                }
                is Resource.Loading -> {
                    _state.value = FoodListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "50"
        queries["apiKey"] = API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        Log.d("TAG", "applyQueries: $queries")

        return queries
    }

}