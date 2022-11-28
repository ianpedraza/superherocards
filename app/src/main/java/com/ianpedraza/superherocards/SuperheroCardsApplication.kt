package com.ianpedraza.superherocards

import android.app.Application
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase

class SuperheroCardsApplication : Application() {
    private val dummyDataSource by lazy {
        CardsLocalDataSource
    }

    private val defaultCardsRepository by lazy {
        DefaultCardsRepository(dummyDataSource)
    }

    val getAllCardsUseCase by lazy {
        GetAllCardsUseCase(defaultCardsRepository)
    }
}
