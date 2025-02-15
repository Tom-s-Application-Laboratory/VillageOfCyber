package com.example.villageofcyber.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.presentation.screen.InGameScreen
import com.example.villageofcyber.levelSelection.presentation.screen.LevelSelectionScreen
import com.example.villageofcyber.title.presentation.screen.TitleScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController,
    innerPadding: PaddingValues
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
            val characterPortraitIds: List<Int> = listOf(
                R.drawable.mini_girl,
                R.drawable.mini_widow,
                R.drawable.mini_woman,
                R.drawable.mini_brotel,
                R.drawable.mini_dancer,
                R.drawable.mini_singer,
                R.drawable.mini_washer,
                R.drawable.mini_young_man,
                R.drawable.mini_mercenary,
                R.drawable.mini_teacher,
                R.drawable.mini_peerage,
                R.drawable.mini_servant,
                R.drawable.mini_village_girl,
                R.drawable.mini_sword_woman,
                R.drawable.mini_embroidery,
                R.drawable.mini_musician
            ).shuffled()
            InGameScreen(
                modifier = Modifier
                    .padding(innerPadding),
                characterPortraitIds = characterPortraitIds
            )
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