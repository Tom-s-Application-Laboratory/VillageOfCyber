package com.example.villageofcyber.inGame.domain.repository

import com.example.villageofcyber.inGame.domain.modelClass.Character

interface CharacterRepository {
    fun getInitializedCharacters(): List<Character>
}