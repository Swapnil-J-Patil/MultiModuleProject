package com.swapnil.multimoduleproject.di

import android.content.Context
import com.swapnil.multimoduleproject.navigation.NavigationSubGraphs
import com.swapnil.search.navigation.SearchFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationSubGraphs(
        searchFeatureApi: SearchFeatureApi,
    ): NavigationSubGraphs {
        return NavigationSubGraphs(searchFeatureApi)
    }


  /*  @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.getRecipeDao()
    }*/

}