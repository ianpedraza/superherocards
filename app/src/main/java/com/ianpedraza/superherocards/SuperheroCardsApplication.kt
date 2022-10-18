package com.ianpedraza.superherocards

import android.app.Application
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.framework.CardsDummyDataSource
import com.ianpedraza.superherocards.usecases.AddFavoriteUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import com.ianpedraza.superherocards.usecases.GetFavoritesUseCase
import com.ianpedraza.superherocards.usecases.RemoveFavoriteUseCase

class SuperheroCardsApplication : Application() {

    private val dummyDataSource: CardsDataSource
        get() = CardsDummyDataSource

    private val repository: CardsRepository
        get() = CardsRepository(dummyDataSource)

    val getAllCardsUseCase: GetAllCardsUseCase
        get() = GetAllCardsUseCase(repository)

    val getFavoritesUseCase: GetFavoritesUseCase
        get() = GetFavoritesUseCase(repository)

    val addFavoritesUseCase: AddFavoriteUseCase
        get() = AddFavoriteUseCase(repository)

    val removeFavoriteUseCase: RemoveFavoriteUseCase
        get() = RemoveFavoriteUseCase(repository)
}
