package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun RoleSticker(
    modifier: Modifier = Modifier,
    role: Int? = null
) {
    if(role == null) {
        return
    }

    Image(
        modifier = modifier,
        painter = painterResource(role),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview
@Composable
private fun RoleStickerPreview() {
    RoleSticker(
        role = R.drawable.prophet1
    )
}