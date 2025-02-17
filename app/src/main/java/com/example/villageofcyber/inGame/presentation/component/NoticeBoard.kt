package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.villageofcyber.R

@Composable
fun NoticeBoard(
    modifier: Modifier = Modifier,
    message: String
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight
        val fontSize = with(LocalDensity.current) { maxHeight.value * 0.04f }.sp

        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(R.drawable.notice_frame),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(vertical = maxHeight * 0.04f, horizontal = maxWidth * 0.06f),
            text = message,
            fontSize = fontSize
        )
    }
}

@Preview
@Composable
private fun NoticeBoardPreview() {
    NoticeBoard(
        modifier = Modifier
            .size(500.dp),
        message = "hellodddddddddddd\ndddddddddddd"
    )
}