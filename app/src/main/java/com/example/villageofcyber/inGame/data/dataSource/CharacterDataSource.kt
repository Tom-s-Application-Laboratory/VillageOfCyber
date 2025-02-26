package com.example.villageofcyber.inGame.data.dataSource

import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Role

interface CharacterDataSource {
    fun getCharacterInformation(): List<CharacterDto>
    fun getShuffledRoles(): List<Role>
    fun getFakeRole(): Role
    fun getRoleStickers(): Map<Role, List<Int>>
}