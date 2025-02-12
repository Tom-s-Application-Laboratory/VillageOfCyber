package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    Box(
        modifier = modifier
    ) {
        Portrait(
            modifier = Modifier
                .width(25.dp),
            who = who
        )
        Row {
            RoleSticker(
                role = role
            )
            Column {
                repeat(factionRevealedByProphet.size) { index ->
                    FactionBadge(
                        faction = factionRevealedByProphet[index]
                    )
                }
            }
            Column {
                repeat(factionRevealedByTraitor.size) { index ->
                    FactionBadge(
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
            R.drawable.traitor2_result_wolf
        )
    )
}