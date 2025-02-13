package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonNextDayKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `onClickTest`() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonNextDay {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonNextDay").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonNextDay").performClick()

        assertTrue(isClicked)
    }
}