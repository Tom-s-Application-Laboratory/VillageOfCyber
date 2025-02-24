package com.example.villageofcyber.inGame.presentation.viewModel

import com.example.villageofcyber.inGame.domain.modelClass.Character

data class InGameState(
    val visibleNoticeBoard: Boolean = true,
    val visibleCommandMenu: Boolean = false,
    val visibleSpeakingSpot: Boolean = false,

    val dayStart: Boolean = true,
    val transparencyOfBlackPanel: Float = 1f,

    val characterPortraitIds: List<Int> = emptyList(),

    val coworkerPlace: List<Character> = emptyList(),
    val wolfTeamPlace: List<Character> = emptyList(),

    val prophetRoleHistory: Map<Character, List<Character>> = emptyMap(),
    val traitorRoleHistory: Map<Character, List<Character>> = emptyMap(),
    val hunterRoleHistory: Map<Character, List<Character>> = emptyMap()
)
