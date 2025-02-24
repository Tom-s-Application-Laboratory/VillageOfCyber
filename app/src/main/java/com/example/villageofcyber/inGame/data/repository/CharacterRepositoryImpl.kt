package com.example.villageofcyber.inGame.data.repository

import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSource
import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dataSource: CharacterDataSource
) : CharacterRepository {
    override fun getInitializedCharacters(): List<Character> {
        val characterInformation: List<CharacterDto> = dataSource.getCharacterInformation()

        val roles: List<Role> = dataSource.getShuffledRoles()

        val characters: MutableList<Character> = mutableListOf()

        repeat(characterInformation.size) { index ->
            val fakeRole: Role = if(roles[index] == Role.WOLF || roles[index] == Role.BETRAYER)
                dataSource.getFakeRole() else Role.NONE

            characters.add(
                index = index,
                element = Character(
                    name = characterInformation[index].name ?: "",
                    miniFace = characterInformation[index].miniFace ?: throw Exception("from getInitializedCharacters"),
                    miniKilledFace = characterInformation[index].miniKilledFace ?: throw Exception("from getInitializedCharacters"),
                    miniAttackedFace = characterInformation[index].miniAttackedFace ?: throw Exception("from getInitializedCharacters"),
                    bigFace = characterInformation[index].bigFace ?: throw Exception("from getInitializedCharacters"),
                    bigDeadFace = characterInformation[index].bigDeadFace ?: throw Exception("from getInitializedCharacters"),
                    alive = characterInformation[index].alive ?: throw Exception("from getInitializedCharacters"),
                    role = roles[index],
                    fakeRole = fakeRole,
                    roleSticker = null,
                    factionBadge = mutableListOf()
                )
            )
        }

        return characters
    }
}