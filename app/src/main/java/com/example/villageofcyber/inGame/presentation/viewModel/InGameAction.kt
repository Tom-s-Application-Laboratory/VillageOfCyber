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
    data object OnClickComingOutHunter: InGameAction
    data object OnClickProphetDirectingBoard: InGameAction
    data object OnClickHunterDirectingBoard: InGameAction
    data object OnClickCloseDirectingBoard: InGameAction
    data object OnClickAutoVoting: InGameAction
    data object OnClickVotingResultPanel: InGameAction
    data object OnClickOpenTargetBoard: InGameAction
    data object OnClickCloseTargetBoard: InGameAction
    data object OnClickChangePageOnTargetBoard: InGameAction
    data class OnClickDirectVoting(val direct: Int): InGameAction
}