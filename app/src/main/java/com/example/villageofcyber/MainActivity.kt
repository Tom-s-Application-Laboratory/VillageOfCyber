package com.example.villageofcyber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.villageofcyber.core.navigation.NavigationRoot
import com.example.villageofcyber.inGame.presentation.viewModel.InGameViewModel
import com.example.villageofcyber.title.presentation.screen.TitleScreen
import com.example.villageofcyber.ui.theme.VillageOfCyberTheme

class MainActivity : ComponentActivity() {
    private val inGameViewModel: InGameViewModel by viewModels { InGameViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VillageOfCyberTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavigationRoot(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun Preview() {
}