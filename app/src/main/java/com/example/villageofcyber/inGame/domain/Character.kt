package com.example.villageofcyber.inGame.domain

data class Character(
    val name: String,
    val miniFace: Int,
    val bigFace: Int,
    val alive: Boolean,
    val role: Int,
    val fakeRole: Int
)
