package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.villageofcyber.R

@Composable
fun SpeechPanel(
    modifier: Modifier = Modifier,
    message: Pair<String, String> = Pair("", "")
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxHeight = maxHeight

        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.message_frame),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            text = message.first,
            color = Color.White,
            fontSize = (maxHeight * 0.15f).value.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = maxHeight * 0.22f),
            text = message.second,
            color = Color.White,
            fontSize = (maxHeight * 0.15f).value.sp
        )
    }
}

@Preview
@Composable
private fun SpeechPanelPreview() {
    SpeechPanel(
        modifier = Modifier
            .width(200.dp)
            .height(80.dp),
        message = Pair("아리스", "살려주세요.")
    )
}