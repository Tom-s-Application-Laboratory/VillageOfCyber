package com.example.villageofcyber.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.villageofcyber.inGame.presentation.screen.InGameScreen
import com.example.villageofcyber.levelSelection.presentation.screen.LevelSelectionScreen
import com.example.villageofcyber.title.presentation.screen.TitleScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.TitleMenu
    ) {
        gameGraph(navController)
    }

}

private fun NavGraphBuilder.gameGraph(navController: NavHostController) {
    navigation<Route>(
        startDestination = Route.TitleMenu
    ) {
        composable<Route.TitleMenu> {
            TitleScreen {       // edge 조절을 해주려면 modifier를 뚫어줘야 할 듯.

            }
        }

        composable<Route.LevelSelection> {
            LevelSelectionScreen {

            }
        }

        composable<Route.InGame> {
            InGameScreen()
        }
    }
}

sealed interface Route {
    @Serializable
    data object TitleMenu: Route

    @Serializable
    data object LevelSelection: Route

    @Serializable
    data object InGame: Route
}