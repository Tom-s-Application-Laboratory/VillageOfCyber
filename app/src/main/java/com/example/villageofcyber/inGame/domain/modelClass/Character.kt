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
    var isTalkingNow: Boolean = false,
    val name: String,

    val miniFace: Int,
    val miniKilledFace: Int,
    val miniAttackedFace: Int,
    val bigFace: Int,
    val bigDeadFace: Int,

    var alive: SurviveStatus,

    val role: Role,
    val fakeRole: Role,

    var roleSticker: Int?,
    val factionBadge: MutableList<Int>,

    val dialogueComingOutFirst: String = "",
    val dialogueComingOutLast: String = "",
    val dialoguePleaseThinkAgain: String = "",
    val dialogueLastComment: String = "",
    val dialogueComingOutCoworkerAlone: String = "",
)