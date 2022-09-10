package com.example.food1.data.repository

import com.example.food1.data.remote.FoodApi
import com.example.food1.domain.model.Result
import com.example.food1.domain.model.ResultX
import com.example.food1.domain.repository.FoodApiRepository
import com.example.food1.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor(
    private val foodApi: FoodApi
): FoodApiRepository {

    override fun getRecipes(queries: Map<String, String>): Flow<Resource<Result>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = foodApi.getRecipes(queries = queries)
                emit(
                    Resource.Success(
                        data = result
                    )
                )
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An expected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            }
        }
    }

}