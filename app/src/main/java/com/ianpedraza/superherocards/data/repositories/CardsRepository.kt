package com.ianpedraza.superherocards.data.repositories

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.domain.models.CardModel

class CardsRepository(private val dataSource: CardsDataSource) {
    fun getAll(): LiveData<List<CardModel>> = dataSource.getAll()

    fun getObtained(): LiveData<List<CardModel>> = dataSource.getObtained()

    fun addObtained(card: CardModel) = dataSource.addObtained(card)

    fun removeObtained(card: CardModel) = dataSource.removeObtained(card)
}
