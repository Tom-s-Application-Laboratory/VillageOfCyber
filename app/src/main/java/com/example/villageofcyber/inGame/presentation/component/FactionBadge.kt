package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun FactionBadge(
    modifier: Modifier = Modifier,
    faction: Int? = null
) {
    if(faction == null) {
        return
    }

    Image(
        modifier = modifier,
        painter = painterResource(faction),
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )
}

@Preview
@Composable
private fun FactionBadgePreview() {
    FactionBadge(
        faction = R.drawable.prophet1_result_wolf
    )
}