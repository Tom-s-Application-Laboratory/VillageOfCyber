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
import com.example.villageofcyber.inGame.presentation.viewModel.InGameAction

@Composable
fun CommandMenu(
    modifier: Modifier = Modifier,
    onAction: (InGameAction) -> Unit = {}
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
                onAction(InGameAction.OnClickComingOutCoworker)
            }
            ButtonComingOutProphet(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                onAction(InGameAction.OnClickComingOutProphet)
            }
            ButtonComingOutTraitor(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                onAction(InGameAction.OnClickComingOutTraitor)
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
                onAction(InGameAction.OnClickCloseCommandMenu)
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