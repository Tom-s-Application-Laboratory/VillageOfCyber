package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character

class GetCharacterMiniFacesUseCase() {
    fun execute(characters: List<Character>): List<Int> {
        val miniFaces: MutableList<Int> = mutableListOf()

        repeat(characters.size) { index ->
            miniFaces.add(
                index = index,
                element = characters[index].miniFace
            )
        }

        return miniFaces
    }
}