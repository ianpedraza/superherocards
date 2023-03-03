package com.ianpedraza.collectiblecards.data.datasources

import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity

interface CardsDataSource {
    fun getAll(): LiveData<List<CardModel>>
    fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>>
    fun getAllObtainedByRarity(rarity: Rarity?): LiveData<List<CardModel>>
    fun getAtPosition(position: Int): CardModel?
    fun getAllObtained(): LiveData<List<CardModel>>
    fun isCardObtained(card: CardModel): LiveData<Boolean>
    fun addObtained(card: CardModel)
    fun removeObtained(card: CardModel)
}
