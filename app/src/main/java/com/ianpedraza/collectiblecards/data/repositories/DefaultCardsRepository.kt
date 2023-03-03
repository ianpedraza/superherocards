package com.ianpedraza.collectiblecards.data.repositories

import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity
import javax.inject.Inject

class DefaultCardsRepository @Inject constructor(
    private val dataSource: CardsDataSource,
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
