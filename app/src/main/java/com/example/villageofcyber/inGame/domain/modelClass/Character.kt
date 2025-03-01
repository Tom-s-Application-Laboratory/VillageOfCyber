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
    var isDisClosed: Boolean = false,

    var roleSticker: Int?,
    val factionBadge: MutableList<Int>,

    val prophetRoleHistory: MutableList<Pair<String, Boolean>>? = null,
    val traitorRoleHistory: MutableList<Pair<String, Boolean>>? = null,
    val hunterRoleHistory: MutableList<Pair<String, Boolean>>? = null,
    val cursorOfHistory: Int = -1,

    val dialogueComingOutFirst: String = "",
    val dialogueComingOutLast: String = "",
    val dialoguePleaseThinkAgain: String = "",
    val dialogueLastComment: String = "",
    val dialogueComingOutCoworkerAlone: String = "",
)