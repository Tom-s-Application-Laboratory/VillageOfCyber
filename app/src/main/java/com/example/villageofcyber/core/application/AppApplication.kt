package com.example.villageofcyber.core.application

import android.app.Application
import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSourceImpl
import com.example.villageofcyber.inGame.data.repository.CharacterRepositoryImpl
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterWhoHasFirstBloodUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterMiniFacesUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCoworkersUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetInitializedCharactersUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetWolfTeamUseCase
import com.example.villageofcyber.inGame.domain.useCase.UpdateCharacterMiniFacesUseCase

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val characterDataSource by lazy { CharacterDataSourceImpl() }
    val characterRepository by lazy { CharacterRepositoryImpl(characterDataSource) }
    val getCharacterMiniFacesUseCase by lazy { GetCharacterMiniFacesUseCase() }
    val getCharacterWhoHasFirstBloodUseCase by lazy { GetCharacterWhoHasFirstBloodUseCase() }
    val updateCharacterMiniFacesUseCase by lazy { UpdateCharacterMiniFacesUseCase() }
    val getCoworkersUseCase by lazy { GetCoworkersUseCase() }
    val getWolfTeamUseCase by lazy { GetWolfTeamUseCase() }
    val getInitializedCharactersUseCase by lazy { GetInitializedCharactersUseCase(
        repository = characterRepository,
        dataSource = characterDataSource,
    ) }
}