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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
import com.example.villageofcyber.inGame.presentation.component.DirectingBoard
import com.example.villageofcyber.inGame.presentation.component.NoticeBoard
import com.example.villageofcyber.inGame.presentation.component.NoticeSpot
import com.example.villageofcyber.inGame.presentation.component.RoleBoard
import com.example.villageofcyber.inGame.presentation.component.SpeakingSpot
import com.example.villageofcyber.inGame.presentation.viewModel.InGameAction
import com.example.villageofcyber.inGame.presentation.viewModel.InGameState

@Composable
fun InGameScreen(
    modifier: Modifier = Modifier,
    state: InGameState = InGameState(),
    characterPortraitIds: List<Int>,
    onAction: (InGameAction) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        onAction(InGameAction.OperateBlackPanel)
        onAction(InGameAction.AnnounceFirstBlood)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .matchParentSize()
        ) {
            Row {
                CharacterBoard(
                    modifier = Modifier
                        .width(260.dp)
                        .height(200.dp),
                    characterPortraitIds = characterPortraitIds,
                    roleSticker = state.roleStickerMap
                )
                Spacer(modifier = Modifier.padding(5.dp))
                DailyStatusPanel(
                    modifier = Modifier
                        .height(200.dp),
                    day = state.day,
                    survivor = state.survivor,
                    attacked = state.attacked,
                    killed = state.killed
                )
            }

            if(state.visibleNoticeBoard) {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                NoticeBoard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    message = state.messageToNoticeBoard
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ButtonOpenCommandMenu(
                        modifier = Modifier
                            .width(120.dp)
                            .height(100.dp)
                    ) {
                        onAction(InGameAction.OnClickOpenCommandMenu)
                    }
                    ButtonDoVoting(
                        modifier = Modifier
                            .width(120.dp)
                            .height(100.dp)
                    ) {

                    }
                }
            }
            if(state.visibleCommandMenu) {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                CommandMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) { inGameAction ->
                    onAction(inGameAction)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                RoleBoard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
            if(state.visibleNoticeSpot) {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                NoticeSpot(
                    modifier = Modifier
                        .width(350.dp)
                        .height(400.dp),
                    who = state.characterFaceWhoIsNotified ?: throw Exception("there is no person to notified"),
                    message = state.messageFromWorld,
                    onClick = {
                        onAction(InGameAction.OnClickNextSpeaking)
                    }
                )
            }
            if(state.visibleSpeakingSpot) {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                SpeakingSpot(
                    modifier = Modifier
                        .width(350.dp)
                        .height(400.dp),
                    who = state.characterFaceWhoIsSpeaking ?: throw Exception("there is no person to speak"),
                    headCounter = state.headCounter,
                    message = state.messageFromSpeaker,
                    onClick = {
                        onAction(InGameAction.OnClickNextSpeaking)
                    }
                )
            }
            if(state.visibleDirectingBoard) {
                DirectingBoard(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
                        .weight(1f),
                    peopleYouCanDirect = state.peopleYouCanDirect,
                    whichMenu = state.whichMenu
                ) { inGameAction ->
                    onAction(inGameAction)
                }
            }
        }
    }
//    BlackPanel(
//        modifier = Modifier
//            .fillMaxSize()
//            .alpha(state.transparencyOfBlackPanel)
//    )
}

@Preview
@Composable
private fun InGameScreenPreview() {
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

    InGameScreen(
        characterPortraitIds = characterPortraitIds
    )
}