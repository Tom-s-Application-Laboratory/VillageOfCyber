package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DailyStatusPanel(
    modifier: Modifier = Modifier,
    day: Int,
    survivor: Int,
    attacked: Int,
    killed: Int
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .width(65.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${day}일째",
                color = Color.White
            )
        }
        Text(
            text = "생존자: ${survivor}명",
            color = Color.White
        )
        Text(
            text = "사망자: ${attacked}명",
            color = Color.White
        )
        Text(
            text = "처형자: ${killed}명",
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun DailyStatusPanelPreview() {
    DailyStatusPanel(
        day = 2,
        survivor = 5,
        attacked = 4,
        killed = 7
    )
}