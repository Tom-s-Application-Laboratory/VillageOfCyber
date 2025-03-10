package com.example.villageofcyber.levelSelection.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun ButtonGameModeNormal(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .clickable {
                onClick()
            }
            .testTag(tag = "ButtonGameModeNormal"),
        painter = painterResource(R.drawable.button_game_mode_normal),
        contentDescription = null
    )
}

@Preview
@Composable
private fun ButtonGameModeNormalPreview() {
    ButtonGameModeNormal(
        onClick = {}
    )
}