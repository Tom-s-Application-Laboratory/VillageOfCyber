package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character

class GetCharacterMiniFacesUseCase {
    fun execute(characters: List<Character>): List<Int> = characters
        .map { it.miniFace }
}