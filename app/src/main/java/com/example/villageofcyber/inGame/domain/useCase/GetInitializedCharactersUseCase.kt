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
                val fakeRole = if (roles[index] == Role.WOLF || roles[index] == Role.BETRAYER)
                    dataSource.getFakeRoles().random()
                else
                    Role.NONE

                val dialogue = if(fakeRole == Role.NONE)
                    dataSource.getCharacterInformation()[index].dialogue[role]
                else
                    dataSource.getCharacterInformation()[index].dialogue[fakeRole]

                val roleHistory: MutableList<Pair<String, Boolean>>? = if(role == Role.PROPHET || role == Role.TRAITOR || role == Role.HUNTER
                    || fakeRole == Role.PROPHET || fakeRole == Role.TRAITOR || fakeRole == Role.HUNTER) mutableListOf() else null

                if(role != Role.CITIZEN && fakeRole != Role.CITIZEN) {
                    character.copy(
                        role = role,
                        fakeRole = fakeRole,
                        dialogueComingOutFirst = dialogue?.get(0) ?: "",
                        dialogueComingOutLast = dialogue?.get(1) ?: "",
                        dialoguePleaseThinkAgain = dialogue?.get(2) ?: "",
                        dialogueLastComment = dialogue?.get(3) ?: "",
                        dialogueComingOutCoworkerAlone = if (role == Role.COWORKER)
                            dialogue?.get(4) ?: "" else "",
                        prophetRoleHistory = if(role == Role.PROPHET || fakeRole == Role.PROPHET) roleHistory else null,
                        traitorRoleHistory = if(role == Role.TRAITOR || fakeRole == Role.TRAITOR) roleHistory else null,
                        hunterRoleHistory =  if(role == Role.HUNTER || fakeRole == Role.HUNTER) roleHistory else null,
                        cursorOfHistory = if(roleHistory != null) 0 else -1
                    )
                } else {
                    character.copy(
                        role = role,
                        fakeRole = fakeRole,
                        dialogueLastComment = dialogue?.get(0) ?: "",
                    )
                }
            }
    }
}