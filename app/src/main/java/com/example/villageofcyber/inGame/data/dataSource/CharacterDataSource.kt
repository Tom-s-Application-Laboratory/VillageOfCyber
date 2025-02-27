package com.example.villageofcyber.inGame.data.dataSource

import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Role

interface CharacterDataSource {
    fun getCharacterInformation(): List<CharacterDto>
    fun getRoles(): List<Role>
    fun getFakeRoles(): List<Role>
    fun getRoleStickers(): Map<Role, List<Int>>
}