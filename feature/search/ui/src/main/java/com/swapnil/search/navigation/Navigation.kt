package com.swapnil.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.swapnil.common.naviation.FeatureApi
import com.swapnil.common.naviation.NavigationRoute
import com.swapnil.common.naviation.NavigationSubGraphRoute
import com.swapnil.search.screens.recipe_list.RecipeList
import com.swapnil.search.screens.recipe_list.RecipeListScreen
import com.swapnil.search.screens.recipe_list.RecipeListViewModel

interface SearchFeatureApi: FeatureApi

class SearchFeatureApiImpl : SearchFeatureApi{
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.navigation( route = NavigationSubGraphRoute.Search.route, startDestination =NavigationRoute.RecipeList.route)
        {

            composable(NavigationRoute.RecipeList.route){
                val viewModel = hiltViewModel<RecipeListViewModel>()
                RecipeListScreen(
                    viewModel = viewModel,
                    navHostController = navHostController
                ) { mealId ->
                    viewModel.onEvent(RecipeList.Event.GoToRecipeDetails(mealId))
                }
            }
            composable(NavigationRoute.RecipeDetails.route){

            }
        }
    }
}