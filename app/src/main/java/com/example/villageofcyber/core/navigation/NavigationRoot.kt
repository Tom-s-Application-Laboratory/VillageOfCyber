package com.example.villageofcyber.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.presentation.screen.InGameScreen
import com.example.villageofcyber.inGame.presentation.viewModel.InGameViewModel
import com.example.villageofcyber.levelSelection.presentation.screen.LevelSelectionScreen
import com.example.villageofcyber.title.presentation.screen.TitleScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController,
    innerPadding: PaddingValues,
    inGameViewModel: InGameViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Route.TitleMenu
    ) {
        composable<Route.TitleMenu> {
            TitleScreen(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                navController.navigate(Route.LevelSelection) {
                    popUpTo(Route.TitleMenu) { inclusive = true }
                }
            }
        }

        composable<Route.LevelSelection> {
            LevelSelectionScreen(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                navController.navigate(Route.InGame) {
                    popUpTo(Route.LevelSelection) { inclusive = true }
                }
            }
        }

        composable<Route.InGame> {
            InGameScreen(
                modifier = Modifier
                    .padding(innerPadding),
                state = inGameViewModel.state.collectAsState().value,
                characterPortraitIds = inGameViewModel.state.collectAsState().value.characterPortraitIds
            ) { inGameAction ->
                inGameViewModel.onAction(inGameAction)
            }
        }
    }
}

@Serializable
sealed interface Route {
    @Serializable
    data object TitleMenu: Route

    @Serializable
    data object LevelSelection: Route

    @Serializable
    data object InGame: Route
}