package com.ianpedraza.superherocards

import android.app.Application
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import com.ianpedraza.superherocards.usecases.GetObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase

class SuperheroCardsApplication : Application() {

    private val dummyDataSource: CardsDataSource
        get() = CardsLocalDataSource

    private val repository: CardsRepository
        get() = CardsRepository(dummyDataSource)

    val getAllCardsUseCase: GetAllCardsUseCase
        get() = GetAllCardsUseCase(repository)

    val getAllByRarityUseCase: GetAllByRarityUseCase
        get() = GetAllByRarityUseCase(repository)

    val getObtainedUseCase: GetObtainedUseCase
        get() = GetObtainedUseCase(repository)

    val addObtainedUseCase: AddObtainedUseCase
        get() = AddObtainedUseCase(repository)

    val removeObtainedUseCase: RemoveObtainedUseCase
        get() = RemoveObtainedUseCase(repository)
}
