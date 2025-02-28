package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

@Composable
fun TranslucentMessagePanel(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    BoxWithConstraints(
        modifier = modifier
            .background(color = Color.Black.copy(alpha = 0.5f))
            .padding(start = 5.dp, top = 5.dp)
    ) {
        val maxHeight = maxHeight
         Text(
            text = message,
            color = Color.White,
            fontSize = (maxHeight * 0.18f).value.sp
        )
    }
}

@Preview
@Composable
private fun TranslucentMessagePanelPreview() {
    TranslucentMessagePanel(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp),
        message = "투표 결과, \n요리나가 처형당했습니다."
    )
}