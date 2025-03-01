package com.example.villageofcyber.inGame.presentation.viewModel

import com.example.villageofcyber.inGame.domain.modelClass.Character

data class InGameState(
    val visibleNoticeBoard: Boolean = true,
    val messageToNoticeBoard: String = "",

    val visibleNoticeSpot: Boolean = false,
    val characterFaceWhoIsNotified: Int? = null,
    val messageFromWorld: String = "",
    val isNotifyingNow: Boolean = false,

    val visibleSpeakingSpot: Boolean = false,
    val characterFaceWhoIsSpeaking: List<Int>? = null,
    val messageFromSpeaker: Pair<String, String> = Pair("", ""),
    val headCounter: Int = 0,

    val visibleCommandMenu: Boolean = false,
    val hasDisclosedCoworker: Boolean = false,
    val hasDisclosedProphet: Boolean = false,
    val hasDisclosedTraitor: Boolean = false,
    val hasDisclosedHunter: Boolean = false,

    val visibleDirectingBoard: Boolean = false,
    val peopleYouCanDirect: List<String> = emptyList(),
    val whichMenu: Int = 0,

    val visibleVotingResultPanel: Boolean = false,
    val messageToVotingResultPanel: String = "",
    val votingBox: MutableList<Pair<String, String>> = mutableListOf(),
    val voteCount: MutableList<Pair<String, Int>> = mutableListOf(),

    val dayStart: Boolean = true,
    val transparencyOfBlackPanel: Float = 1f,


    val characterPortraitIds: List<Int> = emptyList(),
    val roleStickerMap: Map<Int, Int> = emptyMap(),
    val currentProphetRoleSticker: Int = 0,
    val currentTraitorRoleSticker: Int = 0,

    val characters: List<Character> = emptyList(),
    val coworkerPlace: List<Character> = emptyList(),
    val wolfTeamPlace: List<Character> = emptyList(),

    val day: Int = -1,
    val survivor: Int = -1,
    val attacked: Int = -1,
    val killed: Int = -1,
)
