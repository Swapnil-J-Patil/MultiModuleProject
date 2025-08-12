package com.swapnil.search.domain.repository

import com.swapnil.search.domain.model.Recipe
import com.swapnil.search.domain.model.RecipeDetails

interface SearchRepository {

    suspend fun getRecipes(s: String): Result<List<Recipe>>

    suspend fun getRecipeDetails(id: String): Result<RecipeDetails>
}