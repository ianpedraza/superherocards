package com.ianpedraza.superherocards.data.repositories

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity

class DefaultCardsRepository(
    private val dataSource: CardsDataSource
) : CardsRepository {
    override fun getAll(): LiveData<List<CardModel>> = dataSource.getAll()

    override fun getAllObtained(): LiveData<List<CardModel>> = dataSource.getAllObtained()

    override fun isCardObtained(card: CardModel) = dataSource.isCardObtained(card)

    override fun getAtPosition(position: Int): CardModel? = dataSource.getAtPosition(position)

    override fun addObtained(card: CardModel) = dataSource.addObtained(card)

    override fun removeObtained(card: CardModel) = dataSource.removeObtained(card)

    override fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>> =
        dataSource.getAllByRarity(rarity)

    override fun getAllObtainedByRarity(rarity: Rarity?): LiveData<List<CardModel>> =
        dataSource.getAllObtainedByRarity(rarity)
}
