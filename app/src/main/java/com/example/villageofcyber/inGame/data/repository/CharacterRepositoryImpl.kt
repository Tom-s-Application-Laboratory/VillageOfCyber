package com.example.villageofcyber.inGame.data.repository

import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSource
import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dataSource: CharacterDataSource
) : CharacterRepository {
    override fun getCharacters(): List<Character> {
        return dataSource.getCharacterInformation()
            .map {characterInformation ->
                Character(
                    name = characterInformation.name ?: "",
                    miniFace = characterInformation.miniFace ?: throw Exception("from getInitializedCharacters"),
                    miniKilledFace = characterInformation.miniKilledFace ?: throw Exception("from getInitializedCharacters"),
                    miniAttackedFace = characterInformation.miniAttackedFace ?: throw Exception("from getInitializedCharacters"),
                    bigFace = characterInformation.bigFace ?: throw Exception("from getInitializedCharacters"),
                    bigDeadFace = characterInformation.bigDeadFace ?: throw Exception("from getInitializedCharacters"),
                    alive = characterInformation.alive ?: throw Exception("from getInitializedCharacters"),
                    role = Role.NONE,
                    fakeRole = Role.NONE,
                    roleSticker = null,
                    factionBadge = mutableListOf(),
                    dialogueComingOutFirst = "",
                    dialogueComingOutLast = "",
                    dialoguePleaseThinkAgain = "",
                    dialogueLastComment = "",
                    dialogueComingOutCoworkerAlone = "",
                )
            }
    }

//    override fun getInitializedCharacters(): List<Character> {
//        val characterInformation: List<CharacterDto> = dataSource.getCharacterInformation()
//
//        val roles: List<Role> = dataSource.getShuffledRoles()
//
//        val characters: MutableList<Character> = mutableListOf()
//
//        repeat(characterInformation.size) { index ->
//            val fakeRole: Role = if(roles[index] == Role.WOLF || roles[index] == Role.BETRAYER)
//                dataSource.getFakeRole() else Role.NONE
//
//            characters.add(
//                index = index,
//                element = Character(
//                    name = characterInformation[index].name ?: "",
//                    miniFace = characterInformation[index].miniFace ?: throw Exception("from getInitializedCharacters"),
//                    miniKilledFace = characterInformation[index].miniKilledFace ?: throw Exception("from getInitializedCharacters"),
//                    miniAttackedFace = characterInformation[index].miniAttackedFace ?: throw Exception("from getInitializedCharacters"),
//                    bigFace = characterInformation[index].bigFace ?: throw Exception("from getInitializedCharacters"),
//                    bigDeadFace = characterInformation[index].bigDeadFace ?: throw Exception("from getInitializedCharacters"),
//                    alive = characterInformation[index].alive ?: throw Exception("from getInitializedCharacters"),
//                    role = roles[index],
//                    fakeRole = fakeRole,
//                    roleSticker = null,
//                    factionBadge = mutableListOf(),
//                    dialogueComingOutFirst = characterInformation[index].dialogue[roles[index]]?.getOrElse(index = 0, { throw Exception("from getInitializedCharacters") }) ?: "",
//                    dialogueComingOutLast = characterInformation[index].dialogue[roles[index]]?.getOrElse(index = 1, { throw Exception("from getInitializedCharacters") }) ?: "",
//                    dialoguePleaseThinkAgain = characterInformation[index].dialogue[roles[index]]?.getOrElse(index = 2, { throw Exception("from getInitializedCharacters") }) ?: "",
//                    dialogueLastComment = characterInformation[index].dialogue[roles[index]]?.getOrElse(index = 3, { throw Exception("from getInitializedCharacters") }) ?: "",
//                    dialogueComingOutCoworkerAlone = if(roles[index] == Role.COWORKER) characterInformation[index].dialogue[roles[index]]?.getOrElse(index = 4, { throw Exception("from getInitializedCharacters") }) ?: "" else "",
//                )
//            )
//        }
//
//        return characters
//    }

    override fun getRoleStickers(): Map<Role, List<Int>> {
        return dataSource.getRoleStickers()
    }
}