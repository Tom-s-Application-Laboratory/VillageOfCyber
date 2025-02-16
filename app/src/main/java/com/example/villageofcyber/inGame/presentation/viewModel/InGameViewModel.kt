package com.example.villageofcyber.inGame.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InGameViewModel: ViewModel() {
    private var _state = MutableStateFlow(InGameState())
    val state = _state.asStateFlow()

    fun onAction(action: InGameAction) {
        when(action) {
            InGameAction.OnClickCloseCommandMenu -> closeCommandMenu()
            InGameAction.OnClickOpenCommandMenu -> openCommandMenu()
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                InGameViewModel()
            }
        }
    }
}