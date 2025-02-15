package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonOpenCommandMenuKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonOpenCommendMenu click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonOpenCommandMenu {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonOpenCommandMenu").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonOpenCommandMenu").performClick()

        assertTrue(isClicked)
    }
}