package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommandMenu(
    modifier: Modifier = Modifier,
    // to feature from ui
    // onAction: (RegisterAction) -> Unit ; 이렇게 사용자 정의 액션을 정의하면 구조가 깔끔합니다.
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonComingOutCoworker(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
            ButtonComingOutProphet(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
            ButtonComingOutTraitor(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonComingOutHunter(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
            ButtonDirectProphet(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
            ButtonDirectHunter(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonDirectVoting(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
            ButtonCloseCommandMenu(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun CommandMenuPreview() {
    CommandMenu(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    )
}