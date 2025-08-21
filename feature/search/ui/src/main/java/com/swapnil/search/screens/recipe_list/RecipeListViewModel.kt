package com.swapnil.search.screens.recipe_list

import com.swapnil.search.domain.use_cases.GetAllRecipeUseCase
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetAllRecipeUseCase
){
}