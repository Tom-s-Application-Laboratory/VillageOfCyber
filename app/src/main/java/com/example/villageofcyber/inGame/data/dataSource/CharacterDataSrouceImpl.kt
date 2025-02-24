package com.example.villageofcyber.inGame.data.dataSource

import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus

class CharacterDataSrouceImpl : CharacterDataSource {
    val names = listOf(
        "청년",
        "용병",
        "선생",
        "소녀",
        "가수",
        "과부",
        "소사",
        "귀족",
        "용자",
        "세탁",
        "창부",
        "검사",
        "촌낭",
        "수선",
        "부인",
        "음악"
    )
    val miniFaces = listOf(
        R.drawable.mini_young_man,
        R.drawable.mini_mercenary,
        R.drawable.mini_teacher,
        R.drawable.mini_girl,
        R.drawable.mini_singer,
        R.drawable.mini_widow,
        R.drawable.mini_servant,
        R.drawable.mini_peerage,
        R.drawable.mini_dancer,
        R.drawable.mini_washer,
        R.drawable.mini_brothel,
        R.drawable.mini_sword_woman,
        R.drawable.mini_village_girl,
        R.drawable.mini_embroidery,
        R.drawable.mini_woman,
        R.drawable.mini_musician
    )
    val miniKilledFaces = listOf(
        R.drawable.mini_killed_young_man,
        R.drawable.mini_killed_mercenary,
        R.drawable.mini_killed_teacher,
        R.drawable.mini_killed_girl,
        R.drawable.mini_killed_singer,
        R.drawable.mini_killed_widow,
        R.drawable.mini_killed_servant,
        R.drawable.mini_killed_peerage,
        R.drawable.mini_killed_dancer,
        R.drawable.mini_attacked_washer,
        R.drawable.mini_killed_brothel,
        R.drawable.mini_killed_sword_woman,
        R.drawable.mini_killed_village_girl,
        R.drawable.mini_killed_embroidery,
        R.drawable.mini_killed_woman,
        R.drawable.mini_killed_musician
    )
    val miniAttackedFaces = listOf(
        R.drawable.mini_attacked_young_man,
        R.drawable.mini_attacked_mercenary,
        R.drawable.mini_attacked_teacher,
        R.drawable.mini_attacked_girl,
        R.drawable.mini_attacked_singer,
        R.drawable.mini_attacked_widow,
        R.drawable.mini_attacked_servant,
        R.drawable.mini_attacked_peerage,
        R.drawable.mini_attacked_dancer,
        R.drawable.mini_attacked_washer,
        R.drawable.mini_attacked_brothel,
        R.drawable.mini_attacked_sword_woman,
        R.drawable.mini_attacked_village_girl,
        R.drawable.mini_attacked_embroidery,
        R.drawable.mini_attacked_woman,
        R.drawable.mini_attacked_musician
    )

    val bigFaces = listOf(
        R.drawable.young_man,
        R.drawable.mercenary,
        R.drawable.teacher,
        R.drawable.girl,
        R.drawable.singer,
        R.drawable.widow,
        R.drawable.servant,
        R.drawable.peerage,
        R.drawable.dancer,
        R.drawable.washer,
        R.drawable.brothel,
        R.drawable.sword_woman,
        R.drawable.village_girl,
        R.drawable.embroidery,
        R.drawable.woman,
        R.drawable.musician
    )
    val bigDeadFaces = listOf(
        R.drawable.dead_young_man,
        R.drawable.dead_mercenary,
        R.drawable.dead_teacher,
        R.drawable.dead_girl,
        R.drawable.dead_singer,
        R.drawable.dead_widow,
        R.drawable.dead_servant,
        R.drawable.dead_peerage,
        R.drawable.dead_dancer,
        R.drawable.dead_washer,
        R.drawable.dead_brothel,
        R.drawable.dead_sword_woman,
        R.drawable.dead_village_girl,
        R.drawable.dead_embroidery,
        R.drawable.dead_woman,
        R.drawable.dead_musician
    )

    val alives = List(size = 16) { SurviveStatus.ALIVE }

    val role = listOf(
        Role.FIRSTBLOOD,
        Role.CITIZEN,
        Role.CITIZEN,
        Role.CITIZEN,
        Role.CITIZEN,
        Role.CITIZEN,
        Role.CITIZEN,
        Role.COWORKER,
        Role.COWORKER,
        Role.PROPHET,
        Role.TRAITOR,
        Role.HUNTER,
        Role.WOLF,
        Role.WOLF,
        Role.WOLF,
        Role.BETRAYER
    )

    val fakeRoleSet = listOf(
        Role.CITIZEN,
        Role.PROPHET,
        Role.TRAITOR,
        Role.HUNTER
    )

    override fun getCharacterInformation(): List<CharacterDto> {
        val characterInformation: MutableList<CharacterDto> = mutableListOf()

        repeat(times = 16) { index ->
            characterInformation.add(
                element = CharacterDto(
                    name = names[index],
                    miniFace = miniFaces[index],
                    miniKilledFace = miniKilledFaces[index],
                    miniAttackedFace = miniAttackedFaces[index],
                    bigFace = bigFaces[index],
                    bigDeadFace = bigDeadFaces[index],
                    alive = alives[index]
                )
            )
        }

        return characterInformation
    }

    override fun getShuffledRoles(): List<Role> {
        return role.shuffled()
    }

    override fun getFakeRole(): Role {
        return fakeRoleSet[fakeRoleSet.indices.random()]
    }
}