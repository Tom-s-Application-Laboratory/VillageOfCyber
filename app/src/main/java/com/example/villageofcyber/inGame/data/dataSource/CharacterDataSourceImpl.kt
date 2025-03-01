package com.example.villageofcyber.inGame.data.dataSource

import com.example.villageofcyber.R
import com.example.villageofcyber.inGame.data.dto.CharacterDto
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus

class CharacterDataSourceImpl : CharacterDataSource {
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
        R.drawable.mini_killed_washer,
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

    val dialogues: Map<String, Map<Role, List<String>>> = mapOf(
        "청년" to mapOf(
            Role.COWORKER to listOf(
                "제가 공유자에요. 그리고 다른 한 분은 이 분이구요.",
                "네, 제가 다른 공유자에요. 저 분이 제 공유자구요.",
                "잠시만요, 저 공유자에요!",
                "무고한 자가 죽어나가다니...",
                "제가 공유자에요"
            ),
            Role.PROPHET to listOf(
                "제가 예언잡니다.",
                "다른 사람의 말은 믿지 마세요. 제가 진짜 예언잡니다.",
                "잠시만요, 저 예언자에요!",
                "무고한 자가 죽어나가다니..."
            ),
            Role.TRAITOR to listOf(
                "제가 영매삽니다.",
                "다른 사람의 말은 믿지 마세요. 제가 진짜 예언잡니다.",
                "잠시만요, 저 영매사에요!",
                "무고한 자가 죽어나가다니..."
            ),
            Role.HUNTER to listOf(
                "제가 사낭꾼입니다.",
                "다른 사람의 말은 믿지 마세요. 제가 진짜 사냥꾼입니다.",
                "잠시만요, 저 사냥꾼이에요!",
                "무고한 자가 죽어나가다니..."
            ),
            Role.CITIZEN to listOf(
                "무고한 자가 죽어나가다니..."
            )
        ),
        "용병" to mapOf(
            Role.COWORKER to listOf(
                "내가 공유지다. 다른 공유자는 이 녀석이다.",
                "그래, 내가 공유자다. 다른 한 명은 저 녀석이 확실해.",
                "어이가 없군. 난 공유자다!",
                "제기랄, 이런 식으로 나온다 이거지!",
                "내가 공유자다."

            ),
            Role.PROPHET to listOf(
                "내가 예언자다.",
                "저 녀석을 처형하면 다시 평화로워지겠군. 예언자는 나니까.",
                "어이가 없군. 난 예언자다!",
                "제기랄, 이런 식으로 나온다 이거지!"
            ),
            Role.TRAITOR to listOf(
                "내가 영매사다.",
                "저 녀석을 처형하면 다시 평화로워지겠군. 영매사는 나니까.",
                "어이가 없군. 난 영매사다!",
                "제기랄, 이런 식으로 나온다 이거지!"
            ),
            Role.HUNTER to listOf(
                "내가 사냥꾼이다.",
                "저 녀석을 처형하면 다시 평화로워지겠군. 사냥꾼은 나니까.",
                "어이가 없군. 난 사냥꾼이다!",
                "제기랄, 이런 식으로 나온다 이거지!"
            ),
            Role.CITIZEN to listOf(
                "제기랄, 이런 식으로 나온다 이거지!"
            )
        ),
        "선생" to mapOf(
            Role.COWORKER to listOf(
                "제가 공유자입니다. 그리고 다른 한 분은 이 분이구요.",
                "네, 제가 공유자가 맞습니다. 그리고 저 분이 제 공유자구요.",
                "잠시만요, 저 공유잡니다. 다시 한 번 생각해주시죠.",
                "당신들 실수하시는 겁니다.",
                "제가 공유자입니다."
            ),
            Role.PROPHET to listOf(
                "예언자를 부르셨나요?",
                "헛소리를 하시는 군요. 제가 예언잡니다.",
                "잠시만요, 저 예언잡니다. 다시 한 번 생각해주시죠.",
                "당신들 실수하시는 겁니다."
            ),
            Role.TRAITOR to listOf(
                "영매사를 부르셨나요?",
                "헛소리를 하시는 군요. 제가 영매삽니다.",
                "잠시만요, 저 영매삽니다. 다시 한 번 생각해주시죠.",
                "당신들 실수하시는 겁니다."
            ),
            Role.HUNTER to listOf(
                "사냥꾼을 부르셨나요?",
                "헛소리를 하시는 군요. 제가 사냥꾼입니다.",
                "잠시만요, 저 사냥꾼입니다. 다시 한 번 생각해주시죠.",
                "당신들 실수하시는 겁니다."
            ),
            Role.CITIZEN to listOf(
                "당신들 실수하시는 겁니다."
            )
        ),
        "소녀" to mapOf(
            Role.COWORKER to listOf(
                "공유자에요. 이 사람도요.",
                "저도 공유자에요. 저 사람이랑 같이요.",
                "저... 공유잔데...",
                "저... 죽는건가요...",
                "공유자에요."
            ),
            Role.PROPHET to listOf(
                "예언자에요.",
                "제가 예언잔데...",
                "저... 예언잔데...",
                "저... 죽는건가요..."
            ),
            Role.TRAITOR to listOf(
                "영매사에요.",
                "제가 영매산데...",
                "저... 영매산데...",
                "저... 죽는건가요..."
            ),
            Role.HUNTER to listOf(
                "사냥꾼이에요.",
                "제가 사냥꾼인데...",
                "저... 사냥꾼인데...",
                "저... 죽는건가요..."
            ),
            Role.CITIZEN to listOf(
                "저... 죽는건가요..."
            )
        ),
        "가수" to mapOf(
            Role.COWORKER to listOf(
                "제가 공유자에요. 저 분이랑 같이요.",
                "저도 공유자에요. 저 분이랑 같이요.",
                "잠시만요, 저는 공유자에요!",
                "정말 후회하실거에요...",
                "제가 공유자에요."
            ),
            Role.PROPHET to listOf(
                "제가 예언자에요.",
                "제가 정말 예언잡니다. 저 분은 가짜에요.",
                "잠시만요, 저는 예언자에요!",
                "정말 후회하실거에요..."
            ),
            Role.TRAITOR to listOf(
                "제가 영매사에요.",
                "제가 정말 영매삽니다. 저 분은 가짜에요.",
                "잠시만요, 저는 영매사에요!",
                "정말 후회하실거에요..."
            ),
            Role.HUNTER to listOf(
                "제가 사냥꾼이에요.",
                "제가 정말 사냥꾼입니다. 저 분은 가짜에요.",
                "잠시만요, 저는 사냥꾼이에요!",
                "정말 후회하실거에요..."
            ),
            Role.CITIZEN to listOf(
                "정말 후회하실거에요..."
            )
        ),
        "과부" to mapOf(
            Role.COWORKER to listOf(
                "저에요, 공유자. 이 사람이랑 같이요.",
                "네, 저도 공유자에요.",
                "저기, 저 공유자인데요...",
                "저도 곧 따라가게 되었네요...",
                "저에요, 공유자."
            ),
            Role.PROPHET to listOf(
                "제가 예언자에요.",
                "제가 진짜 예언자에요.",
                "저기, 저 예언자인데요...",
                "저도 곧 따라가게 되었네요..."
            ),
            Role.TRAITOR to listOf(
                "제가 영매사에요.",
                "제가 진짜 영매사에요.",
                "저기, 저 영매사인데요...",
                "저도 곧 따라가게 되었네요..."
            ),
            Role.HUNTER to listOf(
                "제가 사냥꾼이에요.",
                "제가 진짜 사냥꾼이에요.",
                "저기, 저 사냥꾼인데요...",
                "저도 곧 따라가게 되었네요..."
            ),
            Role.CITIZEN to listOf(
                "저도 곧 따라가게 되었네요..."
            )
        ),
        "소사" to mapOf(
            Role.COWORKER to listOf(
                "제가 공유자에요! 이 분이랑 같이 공유자랍니다.",
                "네, 저도 공유자에요. 잘 부탁드려요!",
                "기다려 주세요! 저 공유자에요!",
                "저.. 정말 늑대 아니에요... 믿어주세요..",
                "제가 공유자에요!"
            ),
            Role.PROPHET to listOf(
                "예언자를 부르셨나요?",
                "어라? 예언자가 여러 명이네요? 저도 예언잔데...",
                "기다려 주세요! 저 예언자에요!",
                "저.. 정말 늑대 아니에요... 믿어주세요.."
            ),
            Role.TRAITOR to listOf(
                "영매사를 부르셨나요?",
                "어라? 영매사가 여러 명이네요? 저도 영매산데...",
                "기다려 주세요! 저 영매사에요!",
                "저.. 정말 늑대 아니에요... 믿어주세요.."
            ),
            Role.HUNTER to listOf(
                "사냥꾼을 부르셨나요?",
                "어라? 사냥꾼이 여러 명이네요? 저도 사냥꾼인데...",
                "기다려 주세요! 저 사냥꾼이에요!",
                "저.. 정말 늑대 아니에요... 믿어주세요.."
            ),
            Role.CITIZEN to listOf(
                "저.. 정말 늑대 아니에요... 믿어주세요.."
            )
        ),
        "귀족" to mapOf(
            Role.COWORKER to listOf(
                "공유자다. 이 자랑 같이.",
                "그 자가 말한 대로다.",
                "어이, 뭣하는 짓이냐! 난 공유자다!",
                "이것들이 이거 놓지 못 해?!",
                "공유자다."
            ),
            Role.PROPHET to listOf(
                "예언자다.",
                "저 놈을 믿는 바보는 없겠지. 내가 예언자다.",
                "어이, 뭣하는 짓이냐! 난 예언자다!",
                "이것들이 이거 놓지 못 해?!"
            ),
            Role.TRAITOR to listOf(
                "영매사다.",
                "저 놈을 믿는 바보는 없겠지. 내가 영매사다.",
                "어이, 뭣하는 짓이냐! 난 영매사다!",
                "이것들이 이거 놓지 못 해?!"
            ),
            Role.HUNTER to listOf(
                "사냥꾼이다.",
                "저 놈을 믿는 바보는 없겠지. 내가 사냥꾼이다.",
                "어이, 뭣하는 짓이냐! 난 사냥꾼이다!",
                "이것들이 이거 놓지 못 해?!"
            ),
            Role.CITIZEN to listOf(
                "이것들이 이거 놓지 못 해?!"
            )
        ),
        "용자" to mapOf(
            Role.COWORKER to listOf(
                "공유자는 저에요. 이 분이랑 같이요.",
                "저도 공유자에요. 잘 부탁 드려요.",
                "뭐하시는 거에요! 저 공유자에요!",
                "이거 놓으세요! 전 정말 늑대가 아니라구요!",
                "공유자는 저에요."
            ),
            Role.PROPHET to listOf(
                "예언자 부르셨나요?",
                "아무래도 예언자가 한둘이 아닌가보네요. 저도 예언자거든요.",
                "뭐하시는 거에요! 저 예언자에요!",
                "이거 놓으세요! 전 정말 늑대가 아니라구요!"
            ),
            Role.TRAITOR to listOf(
                "영매사 부르셨나요?",
                "아무래도 영매사가 한둘이 아닌가보네요. 저도 영매사거든요.",
                "뭐하시는 거에요! 저 영매사에요!",
                "이거 놓으세요! 전 정말 늑대가 아니라구요!"
            ),
            Role.HUNTER to listOf(
                "사냥꾼 부르셨나요?",
                "아무래도 사냥꾼이 한둘이 아닌가보네요. 저도 사냥꾼이거든요.",
                "뭐하시는 거에요! 저 사냥꾼이에요!",
                "이거 놓으세요! 전 정말 늑대가 아니라구요!"
            ),
            Role.CITIZEN to listOf(
                "이거 놓으세요! 전 정말 늑대가 아니라구요!"
            )
        ),
        "세탁" to mapOf(
            Role.COWORKER to listOf(
                "공유자는 접니다. 이 사람과 같이요.",
                "저도 공유자입니다. 저 사람도 공유잡니다. 틀림 없어요.",
                "잠깐만요, 저는 공유자입니다!",
                "말도 안돼...",
                "공유자는 접니다."
            ),
            Role.PROPHET to listOf(
                "제가 예언자에요.",
                "저 분은 뭐하시는 분인지 모르겠지만, 제가 정말 예언잡니다.",
                "잠깐만요, 저는 예언자입니다!",
                "말도 안돼..."
            ),
            Role.TRAITOR to listOf(
                "제가 영매사에요.",
                "저 분은 뭐하시는 분인지 모르겠지만, 제가 정말 영매삽니다.",
                "잠깐만요, 저는 영매사입니다!",
                "말도 안돼..."
            ),
            Role.HUNTER to listOf(
                "제가 사냥꾼이에요.",
                "저 분은 뭐하시는 분인지 모르겠지만, 제가 정말 사냥꾼입니다.",
                "잠깐만요, 저는 사냥꾼입니다!",
                "말도 안돼..."
            ),
            Role.CITIZEN to listOf(
                "말도 안돼..."
            )
        ),
        "창부" to mapOf(
            Role.COWORKER to listOf(
                "맞아, 내가 공유자야~~ 그리고 저 친구가 내 공유자~~",
                "응응 내가 저 친구의 공유자~",
                "에~~ 나 공유잔데, 이렇게 보내도 괜찮아?",
                "뭐야뭐야, 나 죽이려고...?",
                "맞아, 내가 공유자야~~"
            ),
            Role.PROPHET to listOf(
                "예언자 등장!",
                "뭐야, 그럼 다 같이 사이좋게 예언자 하는거야?",
                "에~~ 나 예언잔데, 이렇게 보내도 괜찮아?",
                "뭐야뭐야, 나 죽이려고...?"
            ),
            Role.TRAITOR to listOf(
                "영매사 등장!",
                "뭐야, 그럼 다 같이 사이좋게 영매사 하는거야?",
                "에~~ 나 영매산데, 이렇게 보내도 괜찮아?",
                "뭐야뭐야, 나 죽이려고...?"
            ),
            Role.HUNTER to listOf(
                "사냥꾼 등장!",
                "뭐야, 그럼 다 같이 사이좋게 사냥꾼 하는거야?",
                "에~~ 나 사냥꾼인데, 이렇게 보내도 괜찮아?",
                "뭐야뭐야, 나 죽이려고...?"
            ),
            Role.CITIZEN to listOf(
                "뭐야뭐야, 나 죽이려고...?"
            )
        ),
        "검사" to mapOf(
            Role.COWORKER to listOf(
                "공유자다. 남은 한 명은 저기 저 놈이다.",
                "앞서 말한대로다.",
                "잠깐, 난 공유자다.",
                "늑대가 판치는 구만.",
                "공유자다."
            ),
            Role.PROPHET to listOf(
                "예언자다.",
                "앞에 놈은 늑대 무리인가 보군. 내가 예언자다.",
                "잠깐, 난 예언자다.",
                "늑대가 판치는 구만."
            ),
            Role.TRAITOR to listOf(
                "영매사다.",
                "앞에 놈은 늑대 무리인가 보군. 내가 영매사다.",
                "잠깐, 난 영매사다.",
                "늑대가 판치는 구만."
            ),
            Role.HUNTER to listOf(
                "사냥꾼이다.",
                "앞에 놈은 늑대 무리인가 보군. 내가 사냥꾼이다.",
                "잠깐, 난 사냥꾼이다.",
                "늑대가 판치는 구만."
            ),
            Role.CITIZEN to listOf(
                "늑대가 판치는 구만."
            )
        ),
        "촌낭" to mapOf(
            Role.COWORKER to listOf(
                "내가 공유자에요! 저 사람이랑 같이요!",
                "나도 공유자에요! 저 사람도 공유자에요, 확실해요!",
                "사실 나 공유자야! 아무도 몰랐지?",
                "사...살려주세요...",
                "내가 공유자에요!"
            ),
            Role.PROPHET to listOf(
                "저에요! 저! 예언자는 저에요!",
                "무슨 소리에요! 예언자는 저라구요!",
                "사실 나 예언자야! 아무도 몰랐지?",
                "사...살려주세요..."
            ),
            Role.TRAITOR to listOf(
                "저에요! 저! 영매사는 저에요!",
                "무슨 소리에요! 영매사는 저라구요!",
                "사실 나 영매사야! 아무도 몰랐지?",
                "사...살려주세요..."
            ),
            Role.HUNTER to listOf(
                "저에요! 저! 사냥꾼은 저에요!",
                "무슨 소리에요! 사냥꾼은 저라구요!",
                "사실 나 사냥꾼이야! 아무도 몰랐지?",
                "사...살려주세요..."
            ),
            Role.CITIZEN to listOf(
                "사...살려주세요..."
            )
        ),
        "수선" to mapOf(
            Role.COWORKER to listOf(
                "저 공유자에요. 저 사람도 같이요.",
                "그래요, 저도 공유자에요. 확실해요.",
                "저기요! 저 공유자라구요!",
                "대체 제가 뭘 잘못했다는거죠?",
                "저 공유자에요."
            ),
            Role.PROPHET to listOf(
                "예언자에요.",
                "저 분들은 뭐죠. 예언자는 저에요.",
                "저기요! 저 예언자라구요!",
                "대체 제가 뭘 잘못했다는거죠?"
            ),
            Role.TRAITOR to listOf(
                "영매사에요.",
                "저 분들은 뭐죠. 영매사는 저에요.",
                "저기요! 저 영매사라구요!",
                "대체 제가 뭘 잘못했다는거죠?"
            ),
            Role.HUNTER to listOf(
                "사냥꾼이에요.",
                "저 분들은 뭐죠. 사냥꾼은 저에요.",
                "저기요! 저 사냥꾼이라구요!",
                "대체 제가 뭘 잘못했다는거죠?"
            ),
            Role.CITIZEN to listOf(
                "대체 제가 뭘 잘못했다는거죠?"
            )
        ),
        "부인" to mapOf(
            Role.COWORKER to listOf(
                "그래요. 내가 공유자죠. 저자와 같이.",
                "맞아요. 내가 공유자죠. 저자와 같이.",
                "잠깐, 나 공유자라구요!",
                "정말 다들 미쳤어요!",
                "그래요. 내가 공유자죠."
            ),
            Role.PROPHET to listOf(
                "내가 예언자에요.",
                "앞에 분은 뭐죠? 내가 예언잔데.",
                "잠깐, 나 예언자라구요!",
                "정말 다들 미쳤어요!"
            ),
            Role.TRAITOR to listOf(
                "제가 영매사에요.",
                "앞에 분은 뭐죠? 제가 영매산데.",
                "잠깐, 나 영매사라구요!",
                "정말 다들 미쳤어요!"
            ),
            Role.HUNTER to listOf(
                "제가 사냥꾼이에요.",
                "앞에 분은 뭐죠? 제가 사냥꾼인데.",
                "잠깐, 나 사냥꾼이라구요!",
                "정말 다들 미쳤어요!"
            ),
            Role.CITIZEN to listOf(
                "정말 다들 미쳤어요!"
            )
        ),
        "음악" to mapOf(
            Role.COWORKER to listOf(
                "네, 제가 공유자죠. 저 분도 같이요.",
                "그래요, 저도 공유자입니다. 저 분이랑 같이요.",
                "잠깐 기다리시죠. 전 공유잡니다.",
                "개자식들 아직도 마녀사냥이 팽배하다니...",
                "네, 제가 공유자죠."
            ),
            Role.PROPHET to listOf(
                "예언자는 접니다.",
                "저 분이 늑대 무리겠네요. 예언자는 접니다.",
                "잠깐 기다리시죠. 전 예언잡니다.",
                "개자식들 아직도 마녀사냥이 팽배하다니..."
            ),
            Role.TRAITOR to listOf(
                "영매사는 접니다.",
                "저 분이 늑대 무리겠네요. 영매사는 접니다.",
                "잠깐 기다리시죠. 전 영매삽니다.",
                "개자식들 아직도 마녀사냥이 팽배하다니..."
            ),
            Role.HUNTER to listOf(
                "사냥꾼은 접니다.",
                "저 분이 늑대 무리겠네요. 사냥꾼은 접니다.",
                "잠깐 기다리시죠. 전 사냥꾼입니다.",
                "개자식들 아직도 마녀사냥이 팽배하다니..."
            ),
            Role.CITIZEN to listOf(
                "개자식들 아직도 마녀사냥이 팽배하다니..."
            )
        )
    )

    val roleStickerSet: Map<Role, List<Int>> = mapOf(
        Role.COWORKER to listOf(
            R.drawable.coworker
        ),
        Role.PROPHET to listOf(
            R.drawable.prophet1,
            R.drawable.prophet2,
            R.drawable.prophet3,
            R.drawable.prophet4,
            R.drawable.prophet5
        ),
        Role.TRAITOR to listOf(
            R.drawable.traitor1,
            R.drawable.traitor2,
            R.drawable.traitor3,
            R.drawable.traitor4
        ),
        Role.HUNTER to listOf(
            R.drawable.hunter
        )
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
                    alive = alives[index],
                    dialogue = dialogues[names[index]] ?: throw Exception("from character dialog")
                )
            )
        }

        return characterInformation
    }

    override fun getRoles(): List<Role> {
        return role
    }

    /////////////////
//    override fun getShuffledRoles(): List<Role> {
//        return role.shuffled()
//    }
//
    /////////////////

    override fun getFakeRoles(): List<Role> {
        return fakeRoleSet
    }

    override fun getRoleStickers(): Map<Role, List<Int>> {
        return roleStickerSet
    }
}