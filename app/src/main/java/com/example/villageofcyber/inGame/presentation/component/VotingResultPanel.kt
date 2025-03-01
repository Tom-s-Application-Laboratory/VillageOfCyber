package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VotingResultPanel(
    modifier: Modifier = Modifier,
    message: String = "",
    onClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
            .background(color = Color.Black.copy(alpha = 0.5f))
            .clickable {
                onClick()
            }
    ) {
        val maxHeight = maxHeight
        val maxWidth = maxWidth
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(start = maxWidth * 0.18f, top = maxHeight * 0.01f)
        ) {
            Text(
                text = "투표 결과",
                color = Color.White,
                fontSize = (maxHeight * 0.035f).value.sp
            )
            Spacer(modifier = Modifier.padding(vertical = maxHeight * 0.03f))
            Text(
                text = message,
                color = Color.White,
                fontSize = (maxHeight * 0.03f).value.sp
            )
            Spacer(modifier = Modifier.padding(vertical = maxHeight * 0.001f))
        }
    }
}

@Preview
@Composable
private fun VotingResultPanelPreview() {
    VotingResultPanel(
        modifier = Modifier
            .width(100.dp)
            .height(200.dp),
        message = "청년: 0표    투표처→소사 \n소사: 1표    투표처→정보"
    )
}