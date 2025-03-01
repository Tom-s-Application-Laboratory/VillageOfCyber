package com.example.villageofcyber.inGame.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.villageofcyber.core.application.AppApplication
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository
import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.modelClass.Role
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterWhoHasFirstBloodUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterMiniFacesUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCoworkersUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetInitializedCharactersUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetWolfTeamUseCase
import com.example.villageofcyber.inGame.domain.useCase.UpdateCharacterMiniFacesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InGameViewModel(
    private val repository: CharacterRepository,
    private val getCharacterMiniFacesUseCase: GetCharacterMiniFacesUseCase,
    private val getCharacterWhoHasFirstBloodUseCase: GetCharacterWhoHasFirstBloodUseCase,
    private val updateCharacterMiniFacesUseCase: UpdateCharacterMiniFacesUseCase,
    private val getCoworkersUseCase: GetCoworkersUseCase,
    private val getWolfTeamUseCase: GetWolfTeamUseCase,
    private val getInitializedCharactersUseCase: GetInitializedCharactersUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow(InGameState())
    val state = _state.asStateFlow()

    private var countOfComingOutProphet = 0
    private var countOfComingOutTraitor = 0

    private val roleStickerMap: MutableMap<Int, Int> = mutableMapOf()
    private val roleStickers: Map<Role, List<Int>> = repository.getRoleStickers()

    private val characters = getInitializedCharactersUseCase.execute().shuffled()

    init {
        val characterPortraitIds: List<Int> = getCharacterMiniFacesUseCase.execute(characters)

        _state.update {
            it.copy(
                characters = characters,
                charactersSurviveStatus = characters.map { character ->  character.alive },
                characterPortraitIds = characterPortraitIds,
                coworkerPlace = getCoworkersUseCase.execute(characters),
                wolfTeamPlace = getWolfTeamUseCase.execute(characters),
                day = 1,
                survivor = 16,
                attacked = 0,
                killed = 0
            )
        }
    }

    fun onAction(action: InGameAction) {
        viewModelScope.launch {
            when (action) {
                InGameAction.OnClickCloseCommandMenu -> closeCommandMenu()
                InGameAction.OnClickOpenCommandMenu -> openCommandMenu()
                InGameAction.OperateBlackPanel -> operateBlackPanel()
                InGameAction.OnClickNextSpeaking -> clickEvent()
                InGameAction.AnnounceFirstBlood -> announceFirstBlood()
                InGameAction.OnClickComingOutCoworker -> comingOutCoworker()
                InGameAction.OnClickComingOutProphet -> comingOutProphet()
                InGameAction.OnClickComingOutTraitor -> comingOutTraitor()
                InGameAction.OnClickComingOutHunter -> comingOutHunter()
                InGameAction.OnClickHunterDirectingBoard -> openDirectingBoard(whichMenu = 2)
                InGameAction.OnClickProphetDirectingBoard -> openDirectingBoard(whichMenu = 1)
                InGameAction.OnClickCloseDirectingBoard -> closeDirectingBoard()
                InGameAction.OnClickAutoVoting -> doVote(direct = -1)
                InGameAction.OnClickVotingResultPanel -> clickEvent()
                InGameAction.OnClickOpenTargetBoard -> openTargetBoard()
                InGameAction.OnClickCloseTargetBoard -> closeTargetBoard()
                InGameAction.OnClickChangePageOnTargetBoard -> changePageOnTargetBoard()
                is InGameAction.OnClickDirectVoting -> doVote(direct = action.direct)
            }
        }
    }

    private fun changePageOnTargetBoard() {
        if(_state.value.currentPageOnTargetBoard == 1) {
            _state.update {
                it.copy(
                    currentPageOnTargetBoard = 2
                )
            }
        } else {
            _state.update {
                it.copy(
                    currentPageOnTargetBoard = 1
                )
            }
        }
    }

    private fun closeTargetBoard() {
        _state.update {
            it.copy(
                visibleTargetBoard = false,
                visibleCommandMenu = true
            )
        }
    }

    private fun openTargetBoard() {
        _state.update {
            it.copy(
                visibleCommandMenu = false,
                visibleTargetBoard = true
            )
        }
    }

    private suspend fun doVote(direct: Int) {
        val candidates = characters.filter { character ->
            character.alive == SurviveStatus.ALIVE
        }

        var personWhoWillBeKilled: Character? = null

        if(direct == -1) {
            var isDeadLock: Boolean = true

            while (isDeadLock) {
                candidates.forEach { candidate ->
                    candidate.voteCount = 0
                }

                _state.value.votingBox.clear()
                _state.value.voteCount.clear()

                characters.filter { character ->
                    character.alive == SurviveStatus.ALIVE
                }.onEach { voter ->

                    val candidate = if (voter.role == Role.COWORKER) {
                        candidates.filter { candidate ->
                            candidate.name != voter.name && candidate.role != Role.COWORKER
                        }.random()
                    } else if (voter.role == Role.WOLF || voter.role == Role.BETRAYER) {
                        candidates.filter { candidate ->
                            candidate.name != voter.name && candidate.role != Role.WOLF && candidate.role != Role.BETRAYER
                        }.random()
                    } else {
                        candidates.filter { candidate ->
                            candidate.name != voter.name
                        }.random()
                    }

                    candidate.voteCount++
                    _state.value.votingBox.add(Pair<String, String>(voter.name, candidate.name))
                }

                candidates.forEach { candidate ->
                    _state.value.voteCount.add(
                        Pair<String, Int>(
                            candidate.name,
                            candidate.voteCount
                        )
                    )
                }


                val text: StringBuilder = StringBuilder()

                repeat(_state.value.votingBox.size) { index ->
                    text.append("${_state.value.votingBox[index].first}: ${_state.value.voteCount[index].second}    투표처 →${_state.value.votingBox[index].second}\n")
                }

                printTextWithTypingEffectOnVotingResultPanel(text = text.toString())

                personWhoWillBeKilled = candidates.reduce { acc, character ->
                    if (acc.voteCount > character.voteCount) acc else character
                }

                isDeadLock = if (
                    candidates.filter {
                        it.voteCount == candidates.reduce { acc, character ->
                            if (acc.voteCount > character.voteCount) acc else character
                        }.voteCount
                    }.size >= 2
                ) true else false
            }
        } else {
            _state.update {
                it.copy(
                    visibleTargetBoard = false
                )
            }
            var isDeadLock: Boolean = true

            while (isDeadLock) {
                candidates.forEach { candidate ->
                    candidate.voteCount = 0
                }

                _state.value.votingBox.clear()
                _state.value.voteCount.clear()

                characters.filter { character ->
                    character.alive == SurviveStatus.ALIVE
                }.onEach { voter ->

                    val candidate = if(characters[direct].name != voter.name) characters[direct] else candidates.filter { candidate ->
                        candidate.name != voter.name
                    }.random()

                    candidate.voteCount++
                    _state.value.votingBox.add(Pair<String, String>(voter.name, candidate.name))
                }

                candidates.forEach { candidate ->
                    _state.value.voteCount.add(
                        Pair<String, Int>(
                            candidate.name,
                            candidate.voteCount
                        )
                    )
                }


                val text: StringBuilder = StringBuilder()

                repeat(_state.value.votingBox.size) { index ->
                    text.append("${_state.value.votingBox[index].first}: ${_state.value.voteCount[index].second}    투표처 →${_state.value.votingBox[index].second}\n")
                }

                printTextWithTypingEffectOnVotingResultPanel(text = text.toString())

                personWhoWillBeKilled = candidates.reduce { acc, character ->
                    if (acc.voteCount > character.voteCount) acc else character
                }

                isDeadLock = if (
                    candidates.filter {
                        it.voteCount == candidates.reduce { acc, character ->
                            if (acc.voteCount > character.voteCount) acc else character
                        }.voteCount
                    }.size >= 2
                ) true else false
            }
        }

        _state.update {
            it.copy(
                visibleSpeakingSpot = true,
                characterFaceWhoIsSpeaking = listOf(
                    personWhoWillBeKilled?.bigFace
                        ?: throw Exception("from doVote / there is no Person to Speak")
                ),
                headCounter = 1
            )
        }

        if (!personWhoWillBeKilled!!.isDisClosed && (personWhoWillBeKilled.role == Role.COWORKER ||
                    personWhoWillBeKilled.role == Role.PROPHET ||
                    personWhoWillBeKilled.role == Role.TRAITOR ||
                    personWhoWillBeKilled.role == Role.HUNTER ||
                    personWhoWillBeKilled.fakeRole == Role.COWORKER ||
                    personWhoWillBeKilled.fakeRole == Role.PROPHET ||
                    personWhoWillBeKilled.fakeRole == Role.TRAITOR ||
                    personWhoWillBeKilled.fakeRole == Role.HUNTER)
        ) {
            printTextWithTypingEffectOnSpeakingSpot(name = personWhoWillBeKilled.name, text = personWhoWillBeKilled.dialoguePleaseThinkAgain)
            if(personWhoWillBeKilled.fakeRole != Role.NONE) {
                endComingOut(listOf(personWhoWillBeKilled), personWhoWillBeKilled.fakeRole)
            } else {
                endComingOut(listOf(personWhoWillBeKilled), personWhoWillBeKilled.role)
            }
        } else {
            printTextWithTypingEffectOnSpeakingSpot(
                name = personWhoWillBeKilled.name,
                text = personWhoWillBeKilled.dialogueLastComment
            )

            _state.update {
                it.copy(
                    visibleNoticeSpot = true,
                    visibleSpeakingSpot = false,
                    characterFaceWhoIsSpeaking = null,
                    characterFaceWhoIsNotified = personWhoWillBeKilled.bigDeadFace,
                    messageFromSpeaker = Pair("", ""),
                    headCounter = 0
                )
            }
            personWhoWillBeKilled.alive = SurviveStatus.KILLED
            printTextWithTypingEffectOnNoticeSpot(text = "투표 결과, \n${personWhoWillBeKilled.name}님이 처형당했습니다.")
        }

        _state.update {
            it.copy(
                visibleSpeakingSpot = false,
                visibleNoticeSpot = false,
                characterFaceWhoIsSpeaking = null,
                messageFromSpeaker = Pair("", ""),
                messageFromWorld = "",
                characterFaceWhoIsNotified = null
            )
        }
        updateCharacterBoard(characters)
    }

    private suspend fun printTextWithTypingEffectOnVotingResultPanel(text: String) {
        _state.update {
            it.copy(
                visibleVotingResultPanel = true,
                visibleNoticeBoard = false
            )
        }

        val textArray = text.toCharArray()
        val buildingText: StringBuffer = StringBuffer()

        repeat(textArray.size) { index ->
            _state.update {
                it.copy(
                    messageToVotingResultPanel = buildingText.append(textArray[index]).toString()
                )
            }
            delay(timeMillis = 10)
        }

        waitForUserClick()

        _state.update {
            it.copy(
                visibleVotingResultPanel = false,
                messageToVotingResultPanel = ""
            )
        }
    }

    private fun closeDirectingBoard() {
        _state.update {
            it.copy(
                visibleDirectingBoard = false,
                visibleNoticeBoard = true
            )
        }
    }

    private fun openDirectingBoard(whichMenu: Int) {
        val names = if (whichMenu == 1) characters.filter { character ->
            character.alive == SurviveStatus.ALIVE && character.isDisClosed && (character.role == Role.PROPHET || character.fakeRole == Role.PROPHET)
        }.map { it.name }.toList() else characters.filter { character ->
            character.alive == SurviveStatus.ALIVE && character.isDisClosed && (character.role == Role.HUNTER || character.fakeRole == Role.HUNTER)
        }.map { it.name }.toList()

        _state.update {
            it.copy(
                visibleDirectingBoard = true,
                visibleCommandMenu = false,
                whichMenu = whichMenu,
                peopleYouCanDirect = names
            )
        }
    }

    private fun playEachRole(direct: Int) {
        val prophetSurvivor = characters.filter { character ->
            character.alive == SurviveStatus.ALIVE && (character.role == Role.PROPHET || character.fakeRole == Role.PROPHET)
        }

        prophetSurvivor.onEach { prophet ->
            val target = if (direct == -1) characters.filter { character ->
                character.alive == SurviveStatus.ALIVE &&
                        character.name != prophet.name &&
                        !prophet.prophetRoleHistory!!.any { it.first == character.name }
            }.random() else characters[direct]

            if (target.role == Role.WOLF) {
                prophet.prophetRoleHistory?.add(Pair<String, Boolean>(target.name, true))
            } else {
                prophet.prophetRoleHistory?.add(Pair<String, Boolean>(target.name, false))
            }
        }

        val traitorSurvivor = characters.filter { character ->
            character.alive == SurviveStatus.ALIVE && (character.role == Role.TRAITOR || character.fakeRole == Role.TRAITOR)
        }

        traitorSurvivor.onEach { traitor ->
            val target = if (direct == -1) characters.filter { character ->
                character.alive == SurviveStatus.KILLED &&
                        !traitor.traitorRoleHistory!!.any { it.first == character.name }
            }.random() else characters[direct]

            if (target.role == Role.WOLF) {
                traitor.traitorRoleHistory?.add(Pair<String, Boolean>(target.name, true))
            } else {
                traitor.traitorRoleHistory?.add(Pair<String, Boolean>(target.name, false))
            }
        }

        val hunterSurvivor = characters.filter { character ->
            character.alive == SurviveStatus.ALIVE && (character.role == Role.HUNTER || character.fakeRole == Role.HUNTER)
        }

        hunterSurvivor.onEach { hunter ->
            val target = if (direct == -1) characters.filter { character ->
                character.alive == SurviveStatus.ALIVE &&
                        character.name != hunter.name &&
                        !hunter.hunterRoleHistory!!.any { it.first == character.name }
            }.random() else characters[direct]

            if (target.role == Role.WOLF) {
                hunter.hunterRoleHistory?.add(Pair<String, Boolean>(target.name, true))
            } else {
                hunter.hunterRoleHistory?.add(Pair<String, Boolean>(target.name, false))
            }
        }
    }

    private suspend fun comingOutCoworker() {
        _state.update {
            it.copy(
                visibleCommandMenu = false,
                visibleNoticeBoard = false
            )
        }

        if (_state.value.hasDisclosedCoworker) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        var noNeedToComingOut: Int = _state.value.coworkerPlace.filter { coworker ->
            coworker.alive != SurviveStatus.ALIVE
        }.size

        _state.value.coworkerPlace.forEach { coworker ->
            if(coworker.isDisClosed) {
                noNeedToComingOut++
            }
        }

        when (noNeedToComingOut) {
            2 -> {
                printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            }

            1 -> {
                val survivor = _state.value.coworkerPlace.filter { coworker ->
                    coworker.alive == SurviveStatus.ALIVE
                }.onEach { coworker ->
                    _state.update {
                        it.copy(
                            visibleSpeakingSpot = true,
                            characterFaceWhoIsSpeaking = listOf(
                                coworker.bigFace
                            ),
                            headCounter = 1
                        )
                    }

                    coworker.isTalkingNow = true
                    printTextWithTypingEffectOnSpeakingSpot(
                        name = coworker.name,
                        text = coworker.dialogueComingOutCoworkerAlone
                    )
                }.toList()

                endComingOut(survivor, Role.COWORKER)
            }

            0 -> {
                val survivor = _state.value.coworkerPlace.onEachIndexed { index, coworker ->
                    _state.update {
                        it.copy(
                            visibleSpeakingSpot = true,
                            characterFaceWhoIsSpeaking = listOf(
                                coworker.bigFace
                            ),
                            headCounter = 1
                        )
                    }

                    if (index == 0) {
                        printTextWithTypingEffectOnSpeakingSpot(
                            name = coworker.name,
                            text = coworker.dialogueComingOutFirst
                        )
                    } else {
                        printTextWithTypingEffectOnSpeakingSpot(
                            name = coworker.name,
                            text = coworker.dialogueComingOutLast
                        )
                    }
                }
                endComingOut(survivor, Role.COWORKER)
            }
        }

        _state.update {
            it.copy(
                hasDisclosedCoworker = true
            )
        }
    }

    private suspend fun comingOutProphet() {
        _state.update {
            it.copy(
                visibleNoticeBoard = false,
                visibleCommandMenu = false
            )
        }

        if (_state.value.hasDisclosedProphet) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        val survivor = characters.filter { character ->
            (character.role == Role.PROPHET || character.fakeRole == Role.PROPHET) && character.alive == SurviveStatus.ALIVE && !character.isDisClosed
        }.onEachIndexed { index, character ->
            _state.update {
                it.copy(
                    visibleSpeakingSpot = true,
                    characterFaceWhoIsSpeaking = listOf(
                        character.bigFace
                    ),
                    headCounter = 1
                )
            }
            if (index == 0) {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutFirst
                )
            } else {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutLast
                )
            }
        }

        endComingOut(survivor, Role.PROPHET)

        _state.update {
            it.copy(
                hasDisclosedProphet = true
            )
        }
    }

    private suspend fun comingOutTraitor() {
        _state.update {
            it.copy(
                visibleNoticeBoard = false,
                visibleCommandMenu = false
            )
        }

        if (_state.value.hasDisclosedTraitor) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        val survivor = characters.filter { character ->
            (character.role == Role.TRAITOR || character.fakeRole == Role.TRAITOR) && character.alive == SurviveStatus.ALIVE && !character.isDisClosed
        }.onEachIndexed { index, character ->
            _state.update {
                it.copy(
                    visibleSpeakingSpot = true,
                    characterFaceWhoIsSpeaking = listOf(
                        character.bigFace
                    ),
                    headCounter = 1
                )
            }
            if (index == 0) {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutFirst
                )
            } else {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutLast
                )
            }
        }

        endComingOut(survivor, Role.TRAITOR)

        _state.update {
            it.copy(
                hasDisclosedTraitor = true
            )
        }
    }

    private suspend fun comingOutHunter() {
        _state.update {
            it.copy(
                visibleNoticeBoard = false,
                visibleCommandMenu = false
            )
        }

        if (_state.value.hasDisclosedHunter) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        val survivor = characters.filter { character ->
            (character.role == Role.HUNTER || character.fakeRole == Role.HUNTER) && character.alive == SurviveStatus.ALIVE && !character.isDisClosed
        }.onEachIndexed { index, character ->
            _state.update {
                it.copy(
                    visibleSpeakingSpot = true,
                    characterFaceWhoIsSpeaking = listOf(
                        character.bigFace
                    ),
                    headCounter = 1
                )
            }
            if (index == 0) {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutFirst
                )
            } else {
                printTextWithTypingEffectOnSpeakingSpot(
                    name = character.name,
                    text = character.dialogueComingOutLast
                )
            }
        }

        endComingOut(survivor, Role.HUNTER)

        _state.update {
            it.copy(
                hasDisclosedHunter = true
            )
        }
    }

    private fun endComingOut(
        disclosedCharacter: List<Character>,
        role: Role
    ) {
        _state.update {
            it.copy(
                visibleNoticeBoard = true,
                visibleNoticeSpot = false,
                visibleSpeakingSpot = false,
                characterFaceWhoIsSpeaking = null,
                characterFaceWhoIsNotified = null,
                messageFromWorld = "",
                messageFromSpeaker = Pair("", ""),
                headCounter = 0
            )
        }
        if(role == Role.PROPHET) {
            disclosedCharacter.forEach { character ->
                character.roleSticker = roleStickers[role]?.get(index = _state.value.currentProphetRoleSticker)
                    ?: throw Exception("from handleNextMessage")
                character.isDisClosed = true
                _state.update {
                    it.copy(
                        currentProphetRoleSticker = _state.value.currentProphetRoleSticker + 1
                    )
                }
            }
        } else if(role == Role.TRAITOR) {
            disclosedCharacter.forEach { character ->
                character.roleSticker = roleStickers[role]?.get(index = _state.value.currentTraitorRoleSticker)
                    ?: throw Exception("from handleNextMessage")
                character.isDisClosed = true
                _state.update {
                    it.copy(
                        currentTraitorRoleSticker = _state.value.currentTraitorRoleSticker + 1
                    )
                }
            }
        } else {
            disclosedCharacter.forEach { character ->
                character.roleSticker = roleStickers[role]?.get(0)
                    ?: throw Exception("from handleNextMessage")
                character.isDisClosed = true
            }
        }
        updateCharacterBoard(characters)
    }

    private suspend fun printTextWithTypingEffectOnNoticeBoard(text: String) {
        _state.update {
            it.copy(
                isNotifyingNow = true
            )
        }

        val textArray = text.toCharArray()
        val buildingText: StringBuffer = StringBuffer()

        repeat(textArray.size) { index ->
            _state.update {
                it.copy(
                    messageToNoticeBoard = buildingText.append(textArray[index]).toString()
                )
            }
            delay(timeMillis = 100)
        }

        _state.update {
            it.copy(
                isNotifyingNow = false
            )
        }
    }

    private val _userActionChannel = Channel<Unit>(Channel.CONFLATED)

    // 사용자 클릭을 처리하는 함수
    fun onUserClick() {
        _userActionChannel.trySend(Unit)
    }

    // 사용자 클릭을 기다리는 함수
    private suspend fun waitForUserClick() {
        _userActionChannel.receive()
    }

    private suspend fun announceFirstBlood() {
        val who = getCharacterWhoHasFirstBloodUseCase.execute(characters)

        _state.update {
            it.copy(
                visibleNoticeSpot = true,
                visibleNoticeBoard = false,
                characterFaceWhoIsNotified = who.bigFace,
            )
        }
        printTextWithTypingEffectOnNoticeSpot(text = "어젯밤 ${who.name}님이...")

        _state.update {
            it.copy(
                characterFaceWhoIsNotified = who.bigDeadFace,
            )
        }
        printTextWithTypingEffectOnNoticeSpot(text = "늑대에게 습격 당했습니다...")

        _state.update {
            it.copy(
                visibleNoticeSpot = false,
                visibleNoticeBoard = true,
                characterFaceWhoIsNotified = null,
                messageFromWorld = "",
            )
        }
        who.alive = SurviveStatus.ATTACKED

        updateCharacterBoard(characters)
        updateDailyStatusPanel(characters)

    }

    private fun updateCharacterBoard(characters: List<Character>) {
        // 이 함수 내용들 기능이 둘 이상으로 나뉠 만큼 커지므로 usecase로 구현해 줄 필요가 있어보입니다.
        characters.forEachIndexed { index, character ->
            when (character.alive) {
                SurviveStatus.ALIVE -> {}
                SurviveStatus.KILLED -> {
                    _state.update {
                        it.copy(
                            characterPortraitIds = updateCharacterMiniFacesUseCase.execute(
                                characters
                            )
                        )
                    }
                }

                SurviveStatus.ATTACKED -> {
                    _state.update {
                        it.copy(
                            characterPortraitIds = updateCharacterMiniFacesUseCase.execute(
                                characters
                            )
                        )
                    }
                }
            }

            if(character.isDisClosed) {
                roleStickerMap[index] = character.roleSticker ?: throw Exception("from updateCharacterBoard / this character hasn't roleSticker")
            }
//            if (character.roleSticker != null) {
//                if (character.role == Role.COWORKER && !_state.value.hasDisclosedCoworker) {   // when 구문을 사용하면 Role을 모두 구현을 강제해서 일단은 if else로 처리
//                    roleStickerMap[index] = roleStickers[Role.COWORKER]?.get(0)
//                        ?: throw Exception("from updateCharacterBoard")
//                } else if ((character.role == Role.PROPHET || character.fakeRole == Role.PROPHET)
//                    && !_state.value.hasDisclosedProphet
//                ) {
//                    roleStickerMap[index] =
//                        repository.getRoleStickers()[Role.PROPHET]?.get(
//                            countOfComingOutProphet
//                        )
//                            ?: throw Exception("from updateCharacterBoard")
//                    countOfComingOutProphet++
//                } else if ((character.role == Role.TRAITOR || character.fakeRole == Role.TRAITOR)
//                    && !_state.value.hasDisclosedTraitor
//                ) {
//                    roleStickerMap[index] =
//                        repository.getRoleStickers()[Role.TRAITOR]?.get(
//                            countOfComingOutTraitor
//                        )
//                            ?: throw Exception("from updateCharacterBoard")
//                    countOfComingOutTraitor++
//                } else if ((character.role == Role.HUNTER || character.fakeRole == Role.HUNTER)
//                    && !_state.value.hasDisclosedHunter
//                ) {
//                    roleStickerMap[index] =
//                        repository.getRoleStickers()[Role.HUNTER]?.get(0)
//                            ?: throw Exception("from updateCharacterBoard")
//                }
//            }
        }
        _state.update {
            it.copy(
                characters = characters,
                roleStickerMap = roleStickerMap,
                charactersSurviveStatus = characters.map { character -> character.alive }
            )
        }
    }

    private fun updateDailyStatusPanel(characters: List<Character>) {
        var survivor: Int = 0
        var attacked: Int = 0
        var killed: Int = 0

        characters.forEach { character ->
            when (character.alive) {
                SurviveStatus.ALIVE -> survivor++
                SurviveStatus.KILLED -> killed++
                SurviveStatus.ATTACKED -> attacked++
            }
        }

        _state.update {
            it.copy(
                survivor = survivor,
                attacked = attacked,
                killed = killed
            )
        }
    }

    private suspend fun printTextWithTypingEffectOnNoticeSpot(
        text: String,
    ) {

        val textArray = text.toCharArray()
        val buildingText: StringBuffer = StringBuffer()

        repeat(textArray.size) { index ->
            _state.update {
                it.copy(
                    messageFromWorld = buildingText.append(textArray[index]).toString()
                )
            }
            delay(timeMillis = 100)
        }

        _state.update {
            it.copy(
                isNotifyingNow = false
            )
        }

        waitForUserClick()
    }

    private suspend fun printTextWithTypingEffectOnSpeakingSpot(
        name: String,
        text: String,
    ) {

        val textArray = text.toCharArray()
        val buildingText: StringBuffer = StringBuffer()

        repeat(textArray.size) { index ->
            _state.update {
                it.copy(
                    messageFromSpeaker = Pair<String, String>(
                        name,
                        buildingText.append(textArray[index]).toString()
                    )
                )
            }
            delay(timeMillis = 100)
        }

        _state.update {
            it.copy(
                isNotifyingNow = false
            )
        }

        waitForUserClick()
    }

    private fun clickEvent() {
        onUserClick()

//        if (!_state.value.isTalkingNow) {

//            _state.value.coworkerPlace.filter { coworker ->
//                coworker.alive == SurviveStatus.ALIVE
//            }.forEach { coworker ->
//                _state.update {
//                    it.copy(
//                        nextMessage = true
//                    )
//
//                }
//            }
//        }
    }

    private fun closeCommandMenu() {
        _state.update {
            it.copy(
                visibleCommandMenu = false,
                visibleNoticeBoard = true
            )
        }
    }

    private fun openCommandMenu() {
        _state.update {
            it.copy(
                visibleCommandMenu = true,
                visibleNoticeBoard = false
            )
        }
    }

    private fun operateBlackPanel() {
        viewModelScope.launch {
            while (_state.value.transparencyOfBlackPanel >= 0f) {
                delay(timeMillis = 20)
                _state.update {
                    it.copy(
                        transparencyOfBlackPanel = (it.transparencyOfBlackPanel - 0.01f)
                    )
                }
            }
            _state.update {
                it.copy(
                    dayStart = false
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()

                val application = this[APPLICATION_KEY] as AppApplication

                val characterRepository = application.characterRepository
                val getCharacterMiniFacesUseCase = application.getCharacterMiniFacesUseCase
                val getCharacterWhoHasFirstBloodUseCase =
                    application.getCharacterWhoHasFirstBloodUseCase
                val updateCharacterMiniFacesUseCase =
                    application.updateCharacterMiniFacesUseCase
                val getCoworkerIndexesUseCase =
                    application.getCoworkersUseCase
                val getWolfTeamIndexesUseCase =
                    application.getWolfTeamUseCase
                val getInitializedCharactersUseCase =
                    application.getInitializedCharactersUseCase

                InGameViewModel(
                    repository = characterRepository,
                    getCharacterMiniFacesUseCase = getCharacterMiniFacesUseCase,
                    getCharacterWhoHasFirstBloodUseCase = getCharacterWhoHasFirstBloodUseCase,
                    updateCharacterMiniFacesUseCase = updateCharacterMiniFacesUseCase,
                    getCoworkersUseCase = getCoworkerIndexesUseCase,
                    getWolfTeamUseCase = getWolfTeamIndexesUseCase,
                    getInitializedCharactersUseCase = getInitializedCharactersUseCase
                )
            }
        }
    }
}