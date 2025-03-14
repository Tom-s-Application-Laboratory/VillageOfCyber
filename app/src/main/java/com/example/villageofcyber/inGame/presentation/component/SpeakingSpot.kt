package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun SpeakingSpot(
    modifier: Modifier = Modifier,
    headCounter: Int = 0,
    who: List<Int>,
    message: Pair<String, String> = Pair("", ""),
    onClick: () -> Unit = {}
) {
    Box(    // 터치 이벤트를 가로채기 위함.
        modifier = Modifier
            .fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = modifier
                .align(Alignment.TopCenter)
        ) {
            val maxWidth = maxWidth
            val maxHeight = maxHeight

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                if(headCounter == 1) {
                    Image(
                        modifier = Modifier
                            .width(maxWidth * 0.4f)
                            .height(maxHeight * 0.45f),
                        painter = painterResource(who[0]),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = maxWidth * 0.1f)
                    ) {
                        repeat(who.size) { index ->
                            Image(
                                modifier = Modifier
                                    .width(maxWidth * 0.4f)
                                    .height(maxHeight * 0.45f),
                                painter = painterResource(who[0]),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null
                            )
                        }
                    }
                }

                SpeechPanel(
                    modifier = Modifier
                        .height(maxHeight * 0.3f)
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp)
                        .clickable {
                            onClick()
                        },
                    message = message
                )
            }

        }
    }
}

@Preview
@Composable
private fun SpeakingSpotPreview() {
    SpeakingSpot(
        modifier = Modifier
            .width(250.dp)
            .height(300.dp),
        headCounter = 2,
        who = listOf(
            R.drawable.brothel
        ),
        message = Pair("아리스", "살려주세요")
    )
}