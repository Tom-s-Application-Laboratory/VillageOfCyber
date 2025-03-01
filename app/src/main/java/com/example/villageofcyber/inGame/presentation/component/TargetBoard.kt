package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus

@Composable
fun TargetBoard(
    modifier: Modifier = Modifier,
    characterPortrait: List<Int>,
    characterSurviveStatus: List<SurviveStatus> = emptyList(),
    page: Int,
    onClickReturnButton: () -> Unit = {},
    onClickPageButton: () -> Unit = {},
    onClickTarget: (Int) -> Unit = {}
) {
    val rows = characterPortrait.chunked(size = 4)

    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight

        Column(
            modifier = Modifier
                .matchParentSize(),
            verticalArrangement = Arrangement.spacedBy(space = maxHeight * 0.1f)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(space = maxWidth * 0.049f)
            ) {
                if(page == 1) {
                    repeat(rows[0].size) { index ->
                        Image(
                            modifier = Modifier
                                .width(maxWidth * 0.2f)
                                .height(maxHeight * 0.3f)
                                .clickable {
                                    if(characterSurviveStatus[index] == SurviveStatus.ALIVE) {
                                        onClickTarget(index)
                                    }
                                },
                            painter = painterResource(id = rows[0][index]),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null
                        )
                    }
                } else {
                    repeat(rows[2].size) { index ->
                        Image(
                            modifier = Modifier
                                .width(maxWidth * 0.2f)
                                .height(maxHeight * 0.3f)
                                .clickable {
                                    if(characterSurviveStatus[8 + index] == SurviveStatus.ALIVE) {
                                        onClickTarget(8 + index)
                                    }
                                },
                            painter = painterResource(id = rows[2][index]),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(space = maxWidth * 0.049f)
            ) {
                if(page == 1) {
                    repeat(rows[1].size) { index ->
                        Image(
                            modifier = Modifier
                                .width(maxWidth * 0.2f)
                                .height(maxHeight * 0.3f)
                                .clickable {
                                    if(characterSurviveStatus[4 + index] == SurviveStatus.ALIVE) {
                                        onClickTarget(4 + index)
                                    }
                                },
                            painter = painterResource(id = rows[1][index]),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null
                        )
                    }
                } else {
                    repeat(rows[3].size) { index ->
                        Image(
                            modifier = Modifier
                                .width(maxWidth * 0.2f)
                                .height(maxHeight * 0.3f)
                                .clickable {
                                    if(characterSurviveStatus[12 + index] == SurviveStatus.ALIVE) {
                                        onClickTarget(12 + index)
                                    }
                                },
                            painter = painterResource(id = rows[3][index]),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonReturn(
                    modifier = Modifier
                        .size(maxWidth * 0.21f),
                    onClick = {
                        onClickReturnButton()
                    }
                )
                if(page == 1) {
                    ButtonNext(
                        modifier = Modifier
                            .width(maxWidth * 0.22f)
                            .height(maxHeight * 0.13f),
                        onClick = {
                            onClickPageButton()
                        }
                    )
                } else {
                    ButtonPrevious(
                        modifier = Modifier
                            .width(maxWidth * 0.22f)
                            .height(maxHeight * 0.13f),
                        onClick = {
                            onClickPageButton()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TargetBoardPreview() {
    val characterPortraitIds: List<Int> = listOf(
        R.drawable.girl,
        R.drawable.widow,
        R.drawable.woman,
        R.drawable.brothel,
        R.drawable.dancer,
        R.drawable.singer,
        R.drawable.washer,
        R.drawable.young_man,
        R.drawable.mercenary,
        R.drawable.teacher,
        R.drawable.peerage,
        R.drawable.servant,
        R.drawable.village_girl,
        R.drawable.sword_woman,
        R.drawable.embroidery,
        R.drawable.musician
    )

    TargetBoard(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp),
        characterPortrait = characterPortraitIds,
        page = 1
    )
}