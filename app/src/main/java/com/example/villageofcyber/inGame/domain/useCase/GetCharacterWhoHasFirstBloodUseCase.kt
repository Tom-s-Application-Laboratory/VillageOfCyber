package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role

class GetCharacterWhoHasFirstBloodUseCase() {
    fun execute(characters: List<Character>): Character {
        characters.forEach { character ->
            if(character.role == Role.FIRSTBLOOD) {
                return character
            }
        }
        throw Exception("there is no character who has FirstBlood")
    }
}