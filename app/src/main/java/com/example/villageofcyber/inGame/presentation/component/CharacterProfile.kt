package com.example.villageofcyber.inGame.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.R

@Composable
fun CharacterProfile(
    modifier: Modifier = Modifier,
    who: Int,
    role: Int? = null,
    factionRevealedByProphet: List<Int> = emptyList(),
    factionRevealedByTraitor: List<Int> = emptyList()
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight

        Portrait(
            modifier = Modifier
                .fillMaxSize(),
            who = who
        )
        Row {
            RoleSticker(
                modifier = Modifier
                    .width(maxWidth * 0.5f)
                    .height(maxHeight * 0.17f),
                role = role
            )
            Column {
                repeat(factionRevealedByProphet.size) { index ->
                    FactionBadge(
                        modifier = Modifier
                            .size(maxWidth * 0.25f),
                        faction = factionRevealedByProphet[index]
                    )
                }
            }
            Column {
                repeat(factionRevealedByTraitor.size) { index ->
                    FactionBadge(
                        modifier = Modifier
                            .size(maxWidth * 0.25f),
                        faction = factionRevealedByTraitor[index]
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterProfilePreview() {
    CharacterProfile(
        who = R.drawable.mini_musician,
        role = R.drawable.prophet1,
        factionRevealedByProphet = listOf(
            R.drawable.prophet1_result_wolf,
            R.drawable.prophet2_result_citizen
        ),
        factionRevealedByTraitor = listOf(
            R.drawable.traitor1_result_citizen,
            R.drawable.traitor2_result_wolf,
            R.drawable.traitor3_result_wolf
        )
    )
}