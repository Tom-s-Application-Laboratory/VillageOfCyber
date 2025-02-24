package com.example.villageofcyber.inGame.presentation.viewModel

import android.util.Log
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
import com.example.villageofcyber.inGame.domain.modelClass.SurviveStatus
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterWhoHasFirstBloodUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterMiniFacesUseCase
import com.example.villageofcyber.inGame.domain.useCase.UpdateCharacterMiniFacesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InGameViewModel(
    private val repository: CharacterRepository,
    private val getCharacterMiniFacesUseCase: GetCharacterMiniFacesUseCase,
    private val getCharacterWhoHasFirstBloodUseCase: GetCharacterWhoHasFirstBloodUseCase,
    private val updateCharacterMiniFacesUseCase: UpdateCharacterMiniFacesUseCase
): ViewModel() {
    private var _state = MutableStateFlow(InGameState())
    val state = _state.asStateFlow()

    private val characters: List<Character> = repository.getInitializedCharacters()

    init {
        val characterPortraitIds: List<Int> = getCharacterMiniFacesUseCase.execute(characters)

        _state.update {
            it.copy(
                characterPortraitIds = characterPortraitIds
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
        printTextWithTypingEffect(text = "어젯밤 ${who.name}님이...")

        viewModelScope.launch {
            while(true) {
                if(_state.value.nextMessage) {
                    _state.update {
                        it.copy(
                            characterFaceWhoIsSpeaking = who.bigDeadFace,
                            // messageFromSpeaker = "늑대에게 습격 당했습니다...",
                            nextMessage = false
                        )
                    }
                    printTextWithTypingEffect(text = "늑대에게 습격 당했습니다...")
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
                }
                delay(timeMillis = 100)
            }
        }
    }

    private fun updateCharacterBoard(characters: List<Character>) {
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
        }
    }
    private fun printTextWithTypingEffect(text: String) {
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
                InGameViewModel(
                    repository = characterRepository,
                    getCharacterMiniFacesUseCase = getCharacterMiniFacesUseCase,
                    getCharacterWhoHasFirstBloodUseCase = getCharacterWhoHasFirstBloodUseCase,
                    updateCharacterMiniFacesUseCase = updateCharacterMiniFacesUseCase
                )
            }
        }
    }
}