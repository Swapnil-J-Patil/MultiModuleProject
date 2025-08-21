package com.swapnil.search.screens.recipe_details

import com.swapnil.common.utils.UiText
import com.swapnil.search.domain.model.Recipe
import com.swapnil.search.domain.model.RecipeDetails

object RecipeDetails {
    data class UiState(
        val isLoading: Boolean = false,
        val error: UiText = UiText.Idle,
        val data: RecipeDetails? = null
    )
    sealed interface Navigation{
        data class GoToRecipeDetails(val id: String): Navigation
    }
    sealed interface Event {
        data class FetchRecipeDetails(val id: String) : Event
    }
}