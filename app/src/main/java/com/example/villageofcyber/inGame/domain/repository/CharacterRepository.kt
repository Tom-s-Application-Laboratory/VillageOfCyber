package com.example.villageofcyber.inGame.domain.repository

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role

interface CharacterRepository {
    fun getCharacters(): List<Character>
    fun getRoleStickers(): Map<Role, List<Int>>
}