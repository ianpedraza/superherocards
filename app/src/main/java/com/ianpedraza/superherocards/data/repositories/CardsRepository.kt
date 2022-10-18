package com.ianpedraza.superherocards.data.repositories

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.domain.models.CardModel

class CardsRepository(private val dataSource: CardsDataSource) {
    fun getAll(): LiveData<List<CardModel>> = dataSource.getAll()

    fun getFavorites(): LiveData<List<CardModel>> = dataSource.getFavorites()

    fun addFavorite(card: CardModel) = dataSource.addFavorite(card)

    fun removeFavorite(card: CardModel) = dataSource.removeFavorite(card)
}
