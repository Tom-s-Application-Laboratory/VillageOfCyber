package com.example.villageofcyber.levelSelection.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonGameModeNormalKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `onClickTest`() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonGameModeNormal {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonGameModeNormal").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonGameModeNormal").performClick()

        assertTrue(isClicked)
    }
}