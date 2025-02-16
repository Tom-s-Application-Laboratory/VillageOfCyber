package com.example.villageofcyber.levelSelection.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R
import com.example.villageofcyber.levelSelection.presentation.component.ButtonGameModeNormal

@Composable
fun LevelSelectionScreen(
    modifier: Modifier = Modifier,
    onButtonGameModeNormalClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 150.dp))
            ButtonGameModeNormal(
                modifier = Modifier
                    .width(300.dp)
                    .height(80.dp)
            ) {
                onButtonGameModeNormalClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LevelSelectionScreenPreview() {
    LevelSelectionScreen(
        onButtonGameModeNormalClick = {}
    )
}