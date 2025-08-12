package com.swapnil.search.data.repository

import com.swapnil.search.data.mappers.toDomain
import com.swapnil.search.data.remote.SearchApiService
import com.swapnil.search.domain.model.Recipe
import com.swapnil.search.domain.model.RecipeDetails
import com.swapnil.search.domain.repository.SearchRepository

class SearchRepoImpl(
    private val searchApiService: SearchApiService
): SearchRepository {
    override suspend fun getRecipes(s: String): Result<List<Recipe>> {
        val response = searchApiService.getRecipes(s)
        return if(response.isSuccessful)
        {
            response.body()?.meals?.let { Result.success(it.toDomain()) }?:run { Result.failure(Exception("No data found")) }
        }
        else{
            Result.failure(Exception(response.message()))
        }
    }

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetails> {
        val response = searchApiService.getRecipeDetails(id)
        return if(response.isSuccessful)
        {
            response.body()?.meals?.let {
                if(it.isNotEmpty())
                {
                   it.first().toDomain().let { Result.success(it) }
                }
                else{
                    Result.failure(Exception("No data found"))
                }
            }?:run { Result.failure(Exception("No data found")) }
    }
        else{
            Result.failure(Exception(response.message()))
        }

        }
}