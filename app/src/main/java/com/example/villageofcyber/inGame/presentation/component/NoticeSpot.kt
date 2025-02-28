package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R

@Composable
fun NoticeSpot(
    modifier: Modifier = Modifier,
    who: Int,
    message: String = "",
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
                Image(
                    modifier = Modifier
                        .width(maxWidth * 0.4f)
                        .height(maxHeight * 0.45f),
                    painter = painterResource(who),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
                TranslucentMessagePanel(
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
private fun NoticeSpotPreview() {
    NoticeSpot(
        modifier = Modifier
            .width(250.dp)
            .height(300.dp),
        who = R.drawable.brothel,
        message = "살려주세요."
    )
}