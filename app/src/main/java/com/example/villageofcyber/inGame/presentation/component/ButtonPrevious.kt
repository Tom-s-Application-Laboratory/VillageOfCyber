package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R

@Composable
fun ButtonPrevious(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        modifier = modifier
            .clickable {
                onClick()
            },
        painter = painterResource(id = R.drawable.button_previous),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview
@Composable
private fun ButtonPreviousPreview() {
    ButtonPrevious(
        modifier = Modifier
            .size(60.dp)
    )
}