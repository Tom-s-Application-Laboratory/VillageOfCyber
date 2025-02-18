package com.example.villageofcyber.inGame.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.villageofcyber.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InGameViewModel: ViewModel() {
    private var _state = MutableStateFlow(InGameState())
    val state = _state.asStateFlow()

    init {
        val characterPortraitIds: List<Int> = listOf(
            R.drawable.mini_girl,
            R.drawable.mini_widow,
            R.drawable.mini_woman,
            R.drawable.mini_brothel,
            R.drawable.mini_dancer,
            R.drawable.mini_singer,
            R.drawable.mini_washer,
            R.drawable.mini_young_man,
            R.drawable.mini_mercenary,
            R.drawable.mini_teacher,
            R.drawable.mini_peerage,
            R.drawable.mini_servant,
            R.drawable.mini_village_girl,
            R.drawable.mini_sword_woman,
            R.drawable.mini_embroidery,
            R.drawable.mini_musician
        ).shuffled()

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
                InGameViewModel()
            }
        }
    }
}