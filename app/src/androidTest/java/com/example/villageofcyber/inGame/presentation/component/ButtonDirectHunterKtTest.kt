package com.example.villageofcyber.inGame.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ButtonDirectHunterKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    // ButtonDirectHunter click 여부를 확인
    @Test
    fun onClickTest() {
        var isClicked = false

        composeTestRule.setContent {
            ButtonDirectHunter {
                isClicked = true
            }
        }

        composeTestRule.onNodeWithTag(testTag = "ButtonDirectHunter").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ButtonDirectHunter").performClick()

        assertTrue(isClicked)
    }
}