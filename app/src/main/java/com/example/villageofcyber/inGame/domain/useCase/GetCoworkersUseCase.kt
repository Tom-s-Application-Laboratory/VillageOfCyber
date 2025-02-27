package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository

class GetCoworkersUseCase {
    fun execute(characters: List<Character>): List<Character> = characters
        .filter { character ->
            character.role == Role.COWORKER
        }
}