package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role

class GetCoworkersUseCase {
    fun execute(characters: List<Character>): List<Character> {
        val coworkers: MutableList<Character> = mutableListOf()

        characters.forEachIndexed { index, character ->
            if(character.role == Role.COWORKER) {
                coworkers.add(element = character)
            }
        }

        return coworkers
    }
}