package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R

@Composable
fun ButtonSelectPersonToDirectByNumber(
    modifier: Modifier = Modifier,
    number: Int,
    onClick: () -> Unit = {}
) {
    val image: List<Int> = listOf(
        R.drawable.button_first_person,
        R.drawable.button_second_person,
        R.drawable.button_third_person,
        R.drawable.button_fourth_person,
        R.drawable.button_fifth_person
    )

    Image(
        modifier = modifier
            .clickable {
                onClick()
            },
        painter = painterResource(image[number - 1]),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview
@Composable
private fun ButtonCallPersonByNumberPreview() {
    ButtonSelectPersonToDirectByNumber(
        modifier = Modifier
            .size(30.dp),
        number = 4
    )
}