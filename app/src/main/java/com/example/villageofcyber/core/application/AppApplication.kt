package com.example.villageofcyber.core.application

import android.app.Application
import com.example.villageofcyber.inGame.data.dataSource.CharacterDataSrouceImpl
import com.example.villageofcyber.inGame.data.repository.CharacterRepositoryImpl
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterWhoHasFirstBloodUseCase
import com.example.villageofcyber.inGame.domain.useCase.GetCharacterMiniFacesUseCase

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val characterDataSource by lazy { CharacterDataSrouceImpl() }
    val characterRepository by lazy { CharacterRepositoryImpl(characterDataSource) }
    val getCharacterMiniFacesUseCase by lazy { GetCharacterMiniFacesUseCase() }
    val getCharacterWhoHasFirstBloodUseCase by lazy { GetCharacterWhoHasFirstBloodUseCase() }
}