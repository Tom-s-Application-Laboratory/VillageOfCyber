package com.example.villageofcyber.inGame.presentation.viewModel

sealed interface InGameAction {
    data object OnClickOpenCommandMenu: InGameAction
    data object OnClickCloseCommandMenu: InGameAction
    data object OperateBlackPanel: InGameAction
    data object OnClickNextSpeaking: InGameAction
    data object AnnounceFirstBlood: InGameAction
    data object OnClickComingOutCoworker: InGameAction
    data object OnClickComingOutProphet: InGameAction
    data object OnClickComingOutTraitor: InGameAction
}