package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus

class UpdateCharacterMiniFacesUseCase {
    fun execute(characters: List<Character>): List<Int> {
        val miniFaces: MutableList<Int> = mutableListOf()

        repeat(characters.size) { index ->
            when(characters[index].alive) {
                SurviveStatus.ALIVE -> {
                    miniFaces.add(
                        index = index,
                        element = characters[index].miniFace
                    )
                }
                SurviveStatus.KILLED -> {
                    miniFaces.add(
                        index = index,
                        element = characters[index].miniKilledFace
                    )
                }
                SurviveStatus.ATTACKED -> {
                    miniFaces.add(
                        index = index,
                        element = characters[index].miniAttackedFace
                    )
                }
            }
        }

        return miniFaces
    }
}