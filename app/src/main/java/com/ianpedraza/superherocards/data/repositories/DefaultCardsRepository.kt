package com.ianpedraza.superherocards.data.repositories

import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.domain.models.CardModel

class DefaultCardsRepository(private val dataSource: CardsDataSource) : CardsRepository {
    override fun getAll(): List<CardModel> = dataSource.getAll()
    override fun getAtPosition(position: Int): CardModel? = dataSource.getAtPosition(position)
}
