package com.example.villageofcyber.inGame.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.presentation.component.ButtonDoVoting
import com.example.villageofcyber.inGame.presentation.component.ButtonOpenCommandMenu
import com.example.villageofcyber.inGame.presentation.component.CharacterBoard
import com.example.villageofcyber.inGame.presentation.component.CommandMenu
import com.example.villageofcyber.inGame.presentation.component.DailyStatusPanel
import com.example.villageofcyber.inGame.presentation.component.NoticeBoard

@Composable
fun InGameScreen(
    modifier: Modifier = Modifier,
    characterPortraitIds: List<Int>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column {
            Row {
                CharacterBoard(
                    modifier = modifier
                        .width(280.dp)
                        .height(200.dp),
                    characterPortraitIds = characterPortraitIds
                )
                Spacer(modifier = Modifier.padding(5.dp))
                DailyStatusPanel(
                    modifier = Modifier
                        .height(200.dp),
                    day = 1,
                    survivor = 8,
                    attacked = 4,
                    killed = 4
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            NoticeBoard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                message = "Hello\nwow"
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ButtonOpenCommandMenu(
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp)
                ) {

                }
                ButtonDoVoting(
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp)
                ) {

                }
            }
            CommandMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            )
        }
    }
}

@Preview
@Composable
private fun InGameScreenPreview() {
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

    InGameScreen(
        characterPortraitIds = characterPortraitIds
    )
}