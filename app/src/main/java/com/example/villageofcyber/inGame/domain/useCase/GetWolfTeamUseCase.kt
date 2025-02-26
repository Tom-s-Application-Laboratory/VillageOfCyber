package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role

class GetWolfTeamUseCase {
    fun execute(characters: List<Character>): List<Character> {
        val wolfTeam: MutableList<Character> = mutableListOf()

        characters.forEachIndexed { index, character ->
            if(character.role == Role.WOLF || character.role == Role.BETRAYER) {
                wolfTeam.add(element = character)
            }
        }

        return wolfTeam
    }
}