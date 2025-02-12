package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun Portrait(
    modifier: Modifier = Modifier,
    who: Int
) {
    Image(
        modifier = modifier,
        painter = painterResource(who),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview
@Composable
private fun PortraitPreview() {
    Portrait(
        who = R.drawable.mini_musician
    )
}