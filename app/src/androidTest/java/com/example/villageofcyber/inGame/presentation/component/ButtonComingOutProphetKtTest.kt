package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonComingOutProphetKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `onClickTest`() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonComingOutProphet {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutProphet").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutProphet").performClick()

        assertTrue(isClicked)
    }
}