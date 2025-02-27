package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role

class GetWolfTeamUseCase {
    fun execute(characters: List<Character>): List<Character> {

        return characters.filter { character ->
            character.role == Role.WOLF || character.role == Role.BETRAYER
        }
    }
}