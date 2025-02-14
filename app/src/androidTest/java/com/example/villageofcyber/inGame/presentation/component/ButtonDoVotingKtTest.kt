package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonDoVotingKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonNextDay click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonDoVoting {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonDoVoting").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonDoVoting").performClick()

        assertTrue(isClicked)
    }
}