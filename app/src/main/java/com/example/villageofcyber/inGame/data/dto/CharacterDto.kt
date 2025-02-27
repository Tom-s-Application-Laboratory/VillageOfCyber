package com.example.villageofcyber.inGame.data.dto

import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus

data class CharacterDto(
    val name: String?,
    val miniFace: Int?,
    val miniKilledFace: Int?,
    val miniAttackedFace: Int?,
    val bigFace: Int?,
    val bigDeadFace: Int?,
    val alive: SurviveStatus?,
    val dialogue: Map<Role, List<String>>
)
