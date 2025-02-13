package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonDirectProphetKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonDirectProphet click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonDirectProphet {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonDirectProphet").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonDirectProphet").performClick()

        assertTrue(isClicked)
    }
}