package com.example.villageofcyber.inGame.presentation.viewModel

sealed interface InGameAction {
    data object OnClickOpenCommandMenu: InGameAction
    data object OnClickCloseCommandMenu: InGameAction
    data object OperateBlackPanel: InGameAction
    data object OnClickNextSpeaking: InGameAction
    data object AnnounceFirstBlood: InGameAction
}