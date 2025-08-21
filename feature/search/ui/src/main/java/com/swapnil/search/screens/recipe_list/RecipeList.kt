package com.swapnil.search.screens.recipe_list

import com.swapnil.common.utils.UiText

object RecipeList {
     data class UiState(
         val isLoading: Boolean = false,
         val error: UiText = UiText.Idle,
     )
    sealed interface Navigation{

    }
}