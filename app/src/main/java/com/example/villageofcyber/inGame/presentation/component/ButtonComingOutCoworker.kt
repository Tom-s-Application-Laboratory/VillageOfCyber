package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun ButtonComingOutCoworker(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .testTag(tag = "ButtonComingOutCoworker"),
        painter = painterResource(R.drawable.button_coming_out_coworker),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview
@Composable
private fun ButtonComingOutCoworkerPreview() {
    ButtonComingOutCoworker(
        onClick = {}
    )
}