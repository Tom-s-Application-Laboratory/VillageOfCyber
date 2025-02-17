package com.example.villageofcyber.inGame.presentation.viewModel

sealed interface InGameAction {
    data object OnClickOpenCommandMenu: InGameAction
    data object OnClickCloseCommandMenu: InGameAction
    data object operateBlackPanel: InGameAction
}