package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonComingOutTraitorKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonComingOutTraitor click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonComingOutTraitor {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutTraitor").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonComingOutTraitor").performClick()

        assertTrue(isClicked)
    }
}