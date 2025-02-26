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
import com.example.villageofcyber.inGame.domain.useCase.GetWolfTeamUseCase
import com.example.villageofcyber.inGame.domain.useCase.UpdateCharacterMiniFacesUseCase
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
    private val getWolfTeamUseCase: GetWolfTeamUseCase
): ViewModel() {
    private var _state = MutableStateFlow(InGameState())
    val state = _state.asStateFlow()

    private val characters: List<Character> = repository.getInitializedCharacters()
    private var countOfComingOutProphet = 0
    private var countOfComingOutTraitor = 0

    private val roleStickerMap: MutableMap<Int, Int> = mutableMapOf()
    private val roleStickers: Map<Role, List<Int>> = repository.getRoleStickers()

    init {
        val characterPortraitIds: List<Int> = getCharacterMiniFacesUseCase.execute(characters)
        val coworkerPlace: List<Character> = getCoworkersUseCase.execute(characters)
        val coworkerSurviveStatus: MutableList<SurviveStatus> = MutableList(size = coworkerPlace.size) { SurviveStatus.ALIVE }
        val wolfTeamPlace: List<Character> = getWolfTeamUseCase.execute(characters)

        _state.update {
            it.copy(
                characterPortraitIds = characterPortraitIds,
                coworkerPlace = coworkerPlace,
                wolfTeamPlace = wolfTeamPlace,
                day = 1,
                survivor = 16,
                attacked = 0,
                killed = 0
            )
        }
    }

    fun onAction(action: InGameAction) {
        when(action) {
            InGameAction.OnClickCloseCommandMenu -> closeCommandMenu()
            InGameAction.OnClickOpenCommandMenu -> openCommandMenu()
            InGameAction.OperateBlackPanel -> operateBlackPanel()
            InGameAction.OnClickNextSpeaking -> turnOnNextMessageToggle()
            InGameAction.AnnounceFirstBlood -> announceFirstBlood()
            InGameAction.OnClickComingOutCoworker -> comingOutCoworker(characters)
        }
    }

    private fun comingOutCoworker(characters: List<Character>) {
        _state.update {
            it.copy(
                visibleCommandMenu = false,
                visibleNoticeBoard = true
            )
        }

        if(_state.value.hasDisclosedCoworker) {
            printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            return
        }

        var dead: Int = 0

        _state.value.coworkerPlace.forEach { coworker ->
            if(coworker.alive != SurviveStatus.ALIVE) {
                dead++
            }
        }

        when(dead) {
            2 -> {
                printTextWithTypingEffectOnNoticeBoard(text = "아무도 대답하지 않았다...")
            }
            1 -> {
                viewModelScope.launch {
                    _state.value.coworkerPlace.forEach { coworker ->
                        if(coworker.alive == SurviveStatus.ALIVE) {
                            _state.update {
                                it.copy(
                                    visibleSpeakingSpot = true,
                                    characterFaceWhoIsSpeaking = coworker.bigFace,
                                )
                            }
                            printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutCoworkerAlone)
                            while(true) {
                                if(_state.value.nextMessage) {
                                    _state.update {
                                        it.copy(
                                            visibleSpeakingSpot = false,
                                            characterFaceWhoIsSpeaking = null,
                                            messageFromSpeaker = "",
                                            nextMessage = false
                                        )
                                    }
                                    coworker.roleSticker = roleStickers[Role.COWORKER]?.get(0) ?: throw Exception("from comingOutCoworker")
                                    updateCharacterBoard(characters)
                                    break
                                }
                                delay(timeMillis = 100)
                            }
                        }
                    }
                }

            }
            0 -> {
                viewModelScope.launch {
                    _state.value.coworkerPlace.forEachIndexed { index, coworker ->
                        _state.update {
                            it.copy(
                                visibleSpeakingSpot = true,
                                characterFaceWhoIsSpeaking = coworker.bigFace,
                            )
                        }

                        if(index == 0) {
                            printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutFirst)
                        } else {
                            printTextWithTypingEffectOnSpeakingSpot(text = coworker.dialogueComingOutLast)
                        }
                        while(true) {
                            if (_state.value.nextMessage) {
                                _state.update {
                                    it.copy(
                                        visibleSpeakingSpot = false,
                                        characterFaceWhoIsSpeaking = null,
                                        messageFromSpeaker = "",
                                        nextMessage = false
                                    )
                                }
                                coworker.roleSticker = roleStickers[Role.COWORKER]?.get(0)
                                    ?: throw Exception("from comingOutCoworker")
                                break
                            }
                            delay(timeMillis = 5)
                        }
                    }
                    updateCharacterBoard(characters)
                }
            }
        }


    }

    private fun printTextWithTypingEffectOnNoticeBoard(text: String) {
        _state.update {
            it.copy(
                isTalkingNow = true
            )
        }

        viewModelScope.launch {
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
    }

    private fun announceFirstBlood() {
        val who = getCharacterWhoHasFirstBloodUseCase.execute(characters)

        _state.update {
            it.copy(
                visibleSpeakingSpot = true,
                characterFaceWhoIsSpeaking = who.bigFace,
            )
        }
        printTextWithTypingEffectOnSpeakingSpot(text = "어젯밤 ${who.name}님이...")

        viewModelScope.launch {
            while(true) {
                if(_state.value.nextMessage) {
                    _state.update {
                        it.copy(
                            characterFaceWhoIsSpeaking = who.bigDeadFace,
                            nextMessage = false
                        )
                    }
                    printTextWithTypingEffectOnSpeakingSpot(text = "늑대에게 습격 당했습니다...")
                    break
                }
                delay(timeMillis = 100)
            }

            while(true) {
                if(_state.value.nextMessage) {
                    _state.update {
                        it.copy(
                            visibleSpeakingSpot = false,
                            characterFaceWhoIsSpeaking = null,
                            messageFromSpeaker = "",
                            nextMessage = false
                        )
                    }
                    who.alive = SurviveStatus.ATTACKED
                    updateCharacterBoard(characters)
                    updateDailyStatusPanel(characters)
                }
                delay(timeMillis = 100)
            }
        }
    }

    private fun updateCharacterBoard(characters: List<Character>) {
        // 이 함수 내용들 기능이 둘 이상으로 나뉠 만큼 커지므로 usecase로 구현해 줄 필요가 있어보입니다.
        characters.forEachIndexed { index, character ->
            when(character.alive) {
                SurviveStatus.ALIVE -> {}
                SurviveStatus.KILLED -> {
                    _state.update {
                        it.copy(
                            characterPortraitIds = updateCharacterMiniFacesUseCase.execute(characters)
                        )
                    }
                }
                SurviveStatus.ATTACKED -> {
                    _state.update {
                        it.copy(
                            characterPortraitIds = updateCharacterMiniFacesUseCase.execute(characters)
                        )
                    }
                }
            }

            if(character.roleSticker != null) {
                if(character.role == Role.COWORKER) {   // when 구문을 사용하면 Role을 모두 구현을 강제해서 일단은 if else로 처리
                    roleStickerMap[index] = roleStickers[Role.COWORKER]?.get(0) ?: throw Exception("from updateCharacterBoard")
                } else if(character.role == Role.PROPHET) {
                    roleStickerMap[index] = repository.getRoleStickers()[Role.PROPHET]?.get(countOfComingOutProphet) ?: throw Exception("from updateCharacterBoard")
                    countOfComingOutProphet++
                } else if(character.role == Role.TRAITOR) {
                    roleStickerMap[index] = repository.getRoleStickers()[Role.TRAITOR]?.get(countOfComingOutTraitor) ?: throw Exception("from updateCharacterBoard")
                    countOfComingOutTraitor++
                } else if(character.role == Role.HUNTER) {
                    roleStickerMap[index] = repository.getRoleStickers()[Role.HUNTER]?.get(0) ?: throw Exception("from updateCharacterBoard")
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
            when(character.alive) {
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

    private fun printTextWithTypingEffectOnSpeakingSpot(text: String) {
        _state.update {
            it.copy(
                isTalkingNow = true
            )
        }

        viewModelScope.launch {
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
        }
    }

    private fun turnOnNextMessageToggle() {
        if(!_state.value.isTalkingNow) {
            _state.update {
                it.copy(
                    nextMessage = true
                )
            }
        }
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
            while(_state.value.transparencyOfBlackPanel >= 0f) {
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
                val characterRepository = (this[APPLICATION_KEY] as AppApplication).characterRepository
                val getCharacterMiniFacesUseCase = (this[APPLICATION_KEY] as AppApplication).getCharacterMiniFacesUseCase
                val getCharacterWhoHasFirstBloodUseCase = (this[APPLICATION_KEY] as AppApplication).getCharacterWhoHasFirstBloodUseCase
                val updateCharacterMiniFacesUseCase = (this[APPLICATION_KEY] as AppApplication).updateCharacterMiniFacesUseCase
                val getCoworkerIndexesUseCase = (this[APPLICATION_KEY] as AppApplication).getCoworkersUseCase
                val getWolfTeamIndexesUseCase = (this[APPLICATION_KEY] as AppApplication).getWolfTeamUseCase

                InGameViewModel(
                    repository = characterRepository,
                    getCharacterMiniFacesUseCase = getCharacterMiniFacesUseCase,
                    getCharacterWhoHasFirstBloodUseCase = getCharacterWhoHasFirstBloodUseCase,
                    updateCharacterMiniFacesUseCase = updateCharacterMiniFacesUseCase,
                    getCoworkersUseCase = getCoworkerIndexesUseCase,
                    getWolfTeamUseCase = getWolfTeamIndexesUseCase
                )
            }
        }
    }
}