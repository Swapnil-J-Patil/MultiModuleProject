package com.swapnil.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.swapnil.common.naviation.FeatureApi
import com.swapnil.common.naviation.NavigationRoute
import com.swapnil.common.naviation.NavigationSubGraphRoute

interface SearchFeatureApi: FeatureApi

class SearchFeatureApiImpl : SearchFeatureApi{
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.navigation( route = NavigationSubGraphRoute.Search.route, startDestination =NavigationRoute.RecipeList.route)
        {

            composable(NavigationRoute.RecipeList.route){

            }
            composable(NavigationRoute.RecipeDetails.route){

            }
        }
    }
}