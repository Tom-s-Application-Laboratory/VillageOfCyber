package com.example.villageofcyber.inGame.presentation.viewModel

import com.example.villageofcyber.inGame.domain.modelClass.Character

data class InGameState(
    val visibleNoticeBoard: Boolean = true,
    val messageToNoticeBoard: String = "",

    val visibleSpeakingSpot: Boolean = false,
    val characterFaceWhoIsSpeaking: Int? = null,
    val messageFromSpeaker: String = "",
    val isTalkingNow: Boolean = false,

    val visibleCommandMenu: Boolean = false,
    val hasDisclosedCoworker: Boolean = false,
    val hasDisclosedProphet: Boolean = false,
    val hasDisclosedTraitor: Boolean = false,
    val hasDisclosedHunter: Boolean = false,

    val dayStart: Boolean = true,
    val transparencyOfBlackPanel: Float = 1f,


    val characterPortraitIds: List<Int> = emptyList(),
    val roleStickerMap: Map<Int, Int> = emptyMap(),

    val characters: List<Character> = emptyList(),
    val coworkerPlace: List<Character> = emptyList(),
    val wolfTeamPlace: List<Character> = emptyList(),

    val day: Int = -1,
    val survivor: Int = -1,
    val attacked: Int = -1,
    val killed: Int = -1,
)
