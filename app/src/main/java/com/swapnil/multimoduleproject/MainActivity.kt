package com.swapnil.multimoduleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swapnil.multimoduleproject.navigation.NavigationSubGraphs
import com.swapnil.multimoduleproject.navigation.RecipeNavigation
import com.swapnil.multimoduleproject.ui.theme.MultiModuleProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationSubGraphs: NavigationSubGraphs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiModuleProjectTheme {
                RecipeNavigation(navigationSubGraphs = navigationSubGraphs)

            }
        }
    }
}

