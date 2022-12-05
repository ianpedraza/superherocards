package com.ianpedraza.superherocards

import android.app.Application
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import com.ianpedraza.superherocards.usecases.GetAllObtainedByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetAtPositionUseCase
import com.ianpedraza.superherocards.usecases.IsCardObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase

class SuperheroCardsApplication : Application() {

    private val dummyDataSource = CardsLocalDataSource

    private val defaultCardsRepository = DefaultCardsRepository(dummyDataSource)

    val getAllCardsUseCase = GetAllCardsUseCase(defaultCardsRepository)

    val getAtPositionUseCase = GetAtPositionUseCase(defaultCardsRepository)

    val getAllByRarityUseCase = GetAllByRarityUseCase(defaultCardsRepository)

    val getAllObtainedUseCase = GetAllObtainedUseCase(defaultCardsRepository)

    val addObtainedUseCase = AddObtainedUseCase(defaultCardsRepository)

    val removeObtainedUseCase = RemoveObtainedUseCase(defaultCardsRepository)

    val getAllObtainedByRarityUseCase = GetAllObtainedByRarityUseCase(defaultCardsRepository)

    val isCardObtainedUseCase = IsCardObtainedUseCase(defaultCardsRepository)
}
