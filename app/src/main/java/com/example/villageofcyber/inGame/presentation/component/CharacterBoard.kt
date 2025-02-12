package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.villageofcyber.R

@Composable
fun CharacterBoard(
    modifier: Modifier = Modifier,
    characterPortraitIds: List<Int>
) {
    val rows = characterPortraitIds.chunked(8)
    Column {
        rows.forEach { row ->
            Row {
                row.forEach { portraitId ->
                    CharacterProfile(
                        who = portraitId
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterBoardPreview() {
    val characterPortraitIds: List<Int> = listOf(
        R.drawable.mini_girl,
        R.drawable.mini_widow,
        R.drawable.mini_woman,
        R.drawable.mini_brotel,
        R.drawable.mini_dancer,
        R.drawable.mini_singer,
        R.drawable.mini_washer,
        R.drawable.mini_young_man,
        R.drawable.mini_mercenary,
        R.drawable.mini_teacher,
        R.drawable.mini_peerage,
        R.drawable.mini_servant,
        R.drawable.mini_village_girl,
        R.drawable.mini_sword_woman,
        R.drawable.mini_embroidery,
        R.drawable.mini_musician
    ).shuffled()

    CharacterBoard(
        characterPortraitIds = characterPortraitIds
    )
}