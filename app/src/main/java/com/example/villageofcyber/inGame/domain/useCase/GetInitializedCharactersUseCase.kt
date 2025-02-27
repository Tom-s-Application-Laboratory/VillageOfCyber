package com.example.villageofcyber.inGame.domain.useCase

import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSource
import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository

class GetInitializedCharactersUseCase(
    private val repository: CharacterRepository,
    private val dataSource: CharacterDataSource,
) {
    fun execute(): List<Character> {
        val roles = dataSource.getRoles().shuffled()

        return repository.getCharacters()
            .mapIndexed { index, character ->
                val role = roles[index]
                val dialogue = dataSource.getCharacterInformation()[index].dialogue[role]
                character.copy(
                    role = role,
                    fakeRole = if (roles[index] == Role.WOLF || roles[index] == Role.BETRAYER)
                        dataSource.getFakeRoles().random()
                    else
                        Role.NONE,
                    dialogueComingOutFirst = dialogue?.get(0) ?: "",
                    dialogueComingOutLast = dialogue?.get(1) ?: "",
                    dialoguePleaseThinkAgain = dialogue?.get(2) ?: "",
                    dialogueLastComment = dialogue?.get(3) ?: "",
                    dialogueComingOutCoworkerAlone = if (roles[index] == Role.COWORKER)
                        dialogue?.get(4) ?: "" else "",
                )
            }
    }
}