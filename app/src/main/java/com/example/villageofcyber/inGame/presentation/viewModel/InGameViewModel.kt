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

    private val characters = getInitializedCharactersUseCase.execute()

    init {

        val characterPortraitIds: List<Int> = getCharacterMiniFacesUseCase.execute(characters)
        val coworkerPlace: List<Character> = getCoworkersUseCase.execute(characters)
        val coworkerSurviveStatus: MutableList<SurviveStatus> =
            MutableList(size = coworkerPlace.size) { SurviveStatus.ALIVE }
        val wolfTeamPlace: List<Character> = getWolfTeamUseCase.execute(characters)

        _state.update {
            it.copy(
                characters = characters,
                characterPortraitIds = characterPortraitIds,
                coworkerPlace = coworkerPlace,
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
                InGameAction.OnClickNextSpeaking -> turnOnNextMessageToggle()
                InGameAction.AnnounceFirstBlood -> announceFirstBlood()
                InGameAction.OnClickComingOutCoworker -> comingOutCoworker(characters)
            }
        }
    }

    private suspend fun comingOutCoworker(characters: List<Character>) {

        _state.update {
            it.copy(
                visibleCommandMenu = false,
                visibleNoticeBoard = true
            )
        }

        if (_state.value.hasDisclosedCoworker) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        val deadCount: Int = _state.value.coworkerPlace.filter { coworker ->
            coworker.alive != SurviveStatus.ALIVE
        }.size

        when (deadCount) {
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
                            characterFaceWhoIsSpeaking = coworker.bigFace,
                        )
                    }

                    coworker.isTalkingNow = true
                    printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutCoworkerAlone)

                }.toList()


                handleNextMessage(survivor)
            }

            0 -> {
                viewModelScope.launch {
                    val survivor = _state.value.coworkerPlace.onEachIndexed { index, coworker ->
                        _state.update {
                            it.copy(
                                visibleSpeakingSpot = true,
                                characterFaceWhoIsSpeaking = coworker.bigFace,
                            )
                        }

                        if (index == 0) {
                            printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutFirst)
                        } else {
                            printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutLast)
                        }

                    }
                    handleNextMessage(survivor)
                    updateCharacterBoard(characters)
                }
            }
        }
    }

    private suspend fun handleNextMessage(
        coworkers: List<Character>,
    ) {
        coworkers.forEach { coworker ->
            _state.update {
                it.copy(
                    visibleSpeakingSpot = false,
                    characterFaceWhoIsSpeaking = null,
                    messageFromSpeaker = "",
                )
            }
            coworker.roleSticker = roleStickers[Role.COWORKER]?.get(0)
                ?: throw Exception("from comingOutCoworker")
        }
        updateCharacterBoard(characters)
    }

    private suspend fun printTextWithTypingEffectOnNoticeBoard(text: String) {
        _state.update {
            it.copy(
                isTalkingNow = true
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
                isTalkingNow = false
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
                visibleSpeakingSpot = true,
                characterFaceWhoIsSpeaking = who.bigFace,
            )
        }
        printTextWithTypingEffectOnSpeakingSpot(text = "어젯밤 ${who.name}님이...")

        _state.update {
            it.copy(
                characterFaceWhoIsSpeaking = who.bigDeadFace,
            )
        }
        printTextWithTypingEffectOnSpeakingSpot(text = "늑대에게 습격 당했습니다...")

        _state.update {
            it.copy(
                visibleSpeakingSpot = false,
                characterFaceWhoIsSpeaking = null,
                messageFromSpeaker = "",
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

            if (character.roleSticker != null) {
                if (character.role == Role.COWORKER) {   // when 구문을 사용하면 Role을 모두 구현을 강제해서 일단은 if else로 처리
                    roleStickerMap[index] = roleStickers[Role.COWORKER]?.get(0)
                        ?: throw Exception("from updateCharacterBoard")
                } else if (character.role == Role.PROPHET) {
                    roleStickerMap[index] =
                        repository.getRoleStickers()[Role.PROPHET]?.get(countOfComingOutProphet)
                            ?: throw Exception("from updateCharacterBoard")
                    countOfComingOutProphet++
                } else if (character.role == Role.TRAITOR) {
                    roleStickerMap[index] =
                        repository.getRoleStickers()[Role.TRAITOR]?.get(countOfComingOutTraitor)
                            ?: throw Exception("from updateCharacterBoard")
                    countOfComingOutTraitor++
                } else if (character.role == Role.HUNTER) {
                    roleStickerMap[index] = repository.getRoleStickers()[Role.HUNTER]?.get(0)
                        ?: throw Exception("from updateCharacterBoard")
                }
            }
        }
        _state.update {
            it.copy(
                roleStickerMap = roleStickerMap
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

    private suspend fun printTextWithTypingEffectOnSpeakingSpot(
        text: String,
    ) {

        val textArray = text.toCharArray()
        val buildingText: StringBuffer = StringBuffer()

        repeat(textArray.size) { index ->
            _state.update {
                it.copy(
                    messageFromSpeaker = buildingText.append(textArray[index]).toString()
                )
            }
            delay(timeMillis = 100)
        }

        _state.update {
            it.copy(
                isTalkingNow = false
            )
        }

        waitForUserClick()
    }

    private fun turnOnNextMessageToggle() {
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
                val getInitializedCharactersUseCase = application.getInitializedCharactersUseCase

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