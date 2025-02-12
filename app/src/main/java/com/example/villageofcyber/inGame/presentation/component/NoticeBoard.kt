package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
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
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(R.drawable.notice_frame),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(vertical = 3.dp, horizontal = 5.dp),
            text = message,
            fontSize = 6.sp
        )
    }
}

@Preview
@Composable
private fun NoticeBoardPreview() {
    NoticeBoard(
        modifier = Modifier
            .size(100.dp),
        message = "hellodddddddddddd\ndddddddddddd"
    )
}