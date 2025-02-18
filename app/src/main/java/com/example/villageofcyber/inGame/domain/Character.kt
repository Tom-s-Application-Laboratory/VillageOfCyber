package com.example.villageofcyber.inGame.domain

enum class Role {
    FIRSTBLOOD,
    CITIZEN,
    COWORKER,
    PROPHET,
    TRAITOR,
    HUNTER,
    WOLF,
    BETRAYER
}

data class Character(
    val name: String,
    val miniFace: Int,
    val bigFace: Int,
    val alive: Boolean,
    val role: Role,
    val fakeRole: Role
)