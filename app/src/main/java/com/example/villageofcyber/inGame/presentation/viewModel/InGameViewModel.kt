package com.example.villageofcyber.inGame.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.villageofcyber.R
import com.example.villageofcyber.core.application.AppApplication
import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSrouceImpl
import com.example.villageofcyber.inGame.data.repository.CharacterRepositoryImpl
import com.example.villageofcyber.inGame.domain.repository.CharacterRepository
import com.example.villageofcyber.inGame.domain.modelClass.Character
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterMiniFacesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InGameViewModel(
    private val repository: CharacterRepository,
    private val getCharacterMiniFacesUseCase: GetCharacterMiniFacesUseCase
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
            InGameAction.operateBlackPanel -> operateBlackPanel()
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
                delay(timeMillis = 200)
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

                InGameViewModel(
                    repository = characterRepository,
                    getCharacterMiniFacesUseCase = getCharacterMiniFacesUseCase
                )
            }
        }
    }
}