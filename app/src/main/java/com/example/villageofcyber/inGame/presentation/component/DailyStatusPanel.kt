package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DailyStatusPanel(
    modifier: Modifier = Modifier,
    day: Int,
    survivor: Int,
    attacked: Int,
    killed: Int
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val fontSize = with(LocalDensity.current) { maxHeight.value * 0.1f }.sp

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .width(65.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${day}일째",
                    color = Color.White,
                    fontSize = fontSize
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),

            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "생존자: ${survivor}명",
                    color = Color.White,
                    fontSize = fontSize
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "사망자: ${attacked}명",
                    color = Color.White,
                    fontSize = fontSize
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "처형자: ${killed}명",
                    color = Color.White,
                    fontSize = fontSize
                )
            }

        }
    }


}

@Preview
@Composable
private fun DailyStatusPanelPreview() {
    DailyStatusPanel(
        modifier = Modifier
            .height(200.dp),
        day = 2,
        survivor = 5,
        attacked = 4,
        killed = 7
    )
}