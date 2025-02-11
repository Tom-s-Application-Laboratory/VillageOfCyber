package com.example.villageofcyber.title.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonGameStartKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // ButtonGameStart click 여부를 확인
    @Test
    fun `onClickTest`() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonGameStart {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonGameStart").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonGameStart").performClick()

        assertTrue(isClicked)
    }
}