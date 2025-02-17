package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonComingOutHunterKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonComingOutHunter click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonComingOutHunter {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutHunter").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutHunter").performClick()

        assertTrue(isClicked)
    }
}