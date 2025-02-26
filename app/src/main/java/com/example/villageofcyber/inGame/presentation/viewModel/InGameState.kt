package com.example.villageofcyber.inGame.presentation.viewModel

import com.example.villageofcyber.inGame.domain.modelClass.Character

data class InGameState(
    val visibleNoticeBoard: Boolean = true,
    val messageToNoticeBoard: String = "",

    val visibleSpeakingSpot: Boolean = false,
    val characterFaceWhoIsSpeaking: Int? = null,
    val messageFromSpeaker: String = "",
    val nextMessage: Boolean = false,
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

    val coworkerPlace: List<Character> = emptyList(),
    val wolfTeamPlace: List<Character> = emptyList(),

    val prophetRoleHistory: Map<Character, List<Character>> = emptyMap(),
    val traitorRoleHistory: Map<Character, List<Character>> = emptyMap(),
    val hunterRoleHistory: Map<Character, List<Character>> = emptyMap(),

    val day: Int = -1,
    val survivor: Int = -1,
    val attacked: Int = -1,
    val killed: Int = -1,

    val comingOutCoworkerSituation: Boolean = false,
)
