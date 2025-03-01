package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.villageofcyber.inGame.presentation.viewModel.InGameAction

@Composable
fun DirectingBoard(
    modifier: Modifier = Modifier,
    peopleYouCanDirect: List<String> = emptyList(),
    whichMenu: Int,
    onAction: (InGameAction) -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxHeight = maxHeight
        val maxWidth = maxWidth

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
        )

        Row {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 3.dp),
                verticalArrangement = Arrangement.spacedBy(space = maxHeight * 0.01f)
            ) {
                items(peopleYouCanDirect.size) { index ->
                    Text(
                        text = "${peopleYouCanDirect[index]}: ${index + 1}번",
                        color = Color.White
                    )
                }
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .weight(1f),
                columns = GridCells.Fixed(count = 2),
                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
                verticalArrangement = Arrangement.spacedBy(space = maxHeight * 0.04f),
                horizontalArrangement = Arrangement.spacedBy(space = maxWidth * 0.04f)
            ) {
                items(peopleYouCanDirect.size) { index ->
                    ButtonSelectPersonToDirectByNumber(
                        modifier = Modifier
                            .size(maxHeight * 0.22f),
                        number = index + 1,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonReturn(
                modifier = Modifier
                    .size(maxHeight * 0.15f),
                onClick = {
                    onAction(InGameAction.OnClickCloseDirectingBoard)
                }
            )
            Spacer(modifier = Modifier.width(maxWidth * 0.4f))
            if(whichMenu == 1) {
                ButtonSamePredict(
                    modifier = Modifier
                        .size(maxHeight * 0.18f)
                )
            } else {
                ButtonSameGuard(
                    modifier = Modifier
                        .size(maxHeight * 0.18f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DirectingBoardPreview() {
    DirectingBoard(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp),
        peopleYouCanDirect = listOf(
            "음악가",
            "소사",
            "청년"
        ),
        whichMenu = 2
    )
}