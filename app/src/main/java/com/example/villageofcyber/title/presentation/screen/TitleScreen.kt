package com.example.villageofcyber.title.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import com.example.villageofcyber.title.presentation.component.ButtonGameStart

@Composable
fun TitleScreen(
    onButtonGameStartClick: () -> Unit
) {
    Box(
        modifier = Modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(R.drawable.title),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ButtonGameStart(
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                onButtonGameStartClick()
            }
            Spacer(modifier = Modifier.padding(120.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleScreenPreview() {
    TitleScreen(
        onButtonGameStartClick = {}
    )
}