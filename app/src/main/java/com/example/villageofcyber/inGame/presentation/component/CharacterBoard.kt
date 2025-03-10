package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R

@Composable
fun CharacterBoard(
    modifier: Modifier = Modifier,
    characterPortraitIds: List<Int>,
    roleSticker: Map<Int, Int> = emptyMap()
) {
    val rows = characterPortraitIds.chunked(8)

    Column(
        modifier = modifier
    ) {
        rows.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier
                    .weight(1f)
            ) {
                row.forEachIndexed { columnIndex, portraitId ->
                    val portraitPlace = rowIndex * 8 + columnIndex
                    CharacterProfile(
                        modifier = Modifier
                            .weight(1f),
                        who = portraitId,
                        role = if(roleSticker.isNotEmpty() && roleSticker.containsKey(portraitPlace)) roleSticker[portraitPlace] else null
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
        R.drawable.mini_brothel,
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
        modifier = Modifier
            .width(300.dp)
            .height(300.dp),
        characterPortraitIds = characterPortraitIds,
        roleSticker = mapOf(
            characterPortraitIds.indexOf(R.drawable.mini_washer) to R.drawable.coworker
        )
    )
}