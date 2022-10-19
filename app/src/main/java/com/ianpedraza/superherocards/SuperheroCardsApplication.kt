package com.ianpedraza.superherocards

import android.app.Application
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import com.ianpedraza.superherocards.usecases.GetAllObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetAtPositionUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase

class SuperheroCardsApplication : Application() {

    private val dummyDataSource: CardsDataSource
        get() = CardsLocalDataSource

    private val defaultCardsRepository: DefaultCardsRepository
        get() = DefaultCardsRepository(dummyDataSource)

    val getAllCardsUseCase: GetAllCardsUseCase
        get() = GetAllCardsUseCase(defaultCardsRepository)

    val getAtPositionUseCase: GetAtPositionUseCase
        get() = GetAtPositionUseCase(defaultCardsRepository)

    val getAllByRarityUseCase: GetAllByRarityUseCase
        get() = GetAllByRarityUseCase(defaultCardsRepository)

    val getAllObtainedUseCase: GetAllObtainedUseCase
        get() = GetAllObtainedUseCase(defaultCardsRepository)

    val addObtainedUseCase: AddObtainedUseCase
        get() = AddObtainedUseCase(defaultCardsRepository)

    val removeObtainedUseCase: RemoveObtainedUseCase
        get() = RemoveObtainedUseCase(defaultCardsRepository)
}
