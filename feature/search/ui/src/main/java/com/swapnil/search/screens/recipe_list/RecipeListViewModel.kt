package com.swapnil.search.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.common.utils.NetworkResult
import com.swapnil.common.utils.UiText
import com.swapnil.search.domain.use_cases.GetAllRecipeUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetAllRecipeUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(RecipeList.UiState())
    val uiState: StateFlow<RecipeList.UiState> get() = _uiState.asStateFlow()

    private val _navigation = Channel<RecipeList.Navigation>()
    val navigation: Flow<RecipeList.Navigation> = _navigation.receiveAsFlow()

    private fun search(q: String) = getRecipesUseCase.invoke(q)
        .onEach { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    _uiState.update { RecipeList.UiState(isLoading = true) }
                }

                is NetworkResult.Error -> {
                    _uiState.update { RecipeList.UiState(error = UiText.RemoteString(result.message.toString())) }

                }

                is NetworkResult.Success -> {
                    _uiState.update { RecipeList.UiState(data = result.data) }

                }
            }

        }.launchIn(viewModelScope)

    fun onEvent(event: RecipeList.Event) {
        when (event) {
            is RecipeList.Event.SearchRecipe -> {
                search(event.q)
            }

            is RecipeList.Event.GoToRecipeDetails -> {
                viewModelScope.launch {
                    _navigation.send(RecipeList.Navigation.GoToRecipeDetails(event.id))
                }
            }

            RecipeList.Event.FavoriteScreen -> viewModelScope.launch {
                _navigation.send(RecipeList.Navigation.GoToFavoriteScreen)
            }
        }
    }
}