package com.example.villageofcyber.inGame.presentation.viewModel

data class InGameState(
    val visibleNoticeBoard: Boolean = true,
    val visibleCommandMenu: Boolean = false,
    val visibleSpeakingSpot: Boolean = false,

    val dayStart: Boolean = true,
    val transparencyOfBlackPanel: Float = 1f,

    val characterPortraitIds: List<Int> = emptyList()
)
