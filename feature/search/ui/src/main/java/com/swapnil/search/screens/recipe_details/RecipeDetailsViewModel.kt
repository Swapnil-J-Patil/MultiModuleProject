package com.swapnil.search.screens.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.common.utils.NetworkResult
import com.swapnil.common.utils.UiText
import com.swapnil.search.domain.use_cases.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val recipeDetailsUseCase: GetRecipeDetailsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetails.UiState())
    val uiState: StateFlow<RecipeDetails.UiState> get() = _uiState.asStateFlow()

    private fun recipeDetails(id: String) = recipeDetailsUseCase.invoke(id)
        .onEach{result ->
            when(result){
                is NetworkResult.Loading -> {
                    _uiState.update { RecipeDetails.UiState(isLoading = true) }
                }
                is NetworkResult.Success -> {
                    _uiState.update { RecipeDetails.UiState(data = result.data) }
                }
                is NetworkResult.Error -> {
                    _uiState.update { RecipeDetails.UiState(error = UiText.RemoteString(result.message.toString())) }
                }
            }

    }.launchIn(viewModelScope)

    fun onEvent(event: RecipeDetails.Event){
        when(event){
            is RecipeDetails.Event.FetchRecipeDetails -> {
                recipeDetails(event.id)
            }
        }
    }
}