package com.swapnil.search.screens.recipe_list

import com.swapnil.common.utils.UiText
import com.swapnil.search.domain.model.Recipe

object RecipeList {
     data class UiState(
         val isLoading: Boolean = false,
         val error: UiText = UiText.Idle,
         val data: List<Recipe>? = null
     )
    sealed interface Navigation {

        data class GoToRecipeDetails(val id: String) : Navigation

        data object GoToFavoriteScreen:Navigation

    }
    sealed interface Event {
        data class SearchRecipe(val q: String) : Event

        data class GoToRecipeDetails(val id: String) : Event

        data object FavoriteScreen:Event
    }
}