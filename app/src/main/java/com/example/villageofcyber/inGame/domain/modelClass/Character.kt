package com.example.villageofcyber.inGame.domain.modelClass

enum class Role {
    NONE,
    FIRSTBLOOD,
    CITIZEN,
    COWORKER,
    PROPHET,
    TRAITOR,
    HUNTER,
    WOLF,
    BETRAYER
}

enum class SurviveStatus {
    ALIVE,
    KILLED,
    ATTACKED
}

data class Character(
    val name: String,

    val miniFace: Int,
    val miniKilledFace: Int,
    val miniAttackedFace: Int,
    val bigFace: Int,
    val bigDeadFace: Int,

    var alive: SurviveStatus,

    val role: Role,
    val fakeRole: Role,

    val roleSticker: Int?,
    val factionBadge: MutableList<Int>
)