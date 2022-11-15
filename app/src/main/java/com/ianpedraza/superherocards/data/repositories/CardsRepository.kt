package com.ianpedraza.superherocards.data.repositories

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity

interface CardsRepository {
    fun getAll(): LiveData<List<CardModel>>
    fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>>
    fun getAllObtainedByRarity(rarity: Rarity?): LiveData<List<CardModel>>
    fun getAtPosition(position: Int): CardModel?
    fun getAllObtained(): LiveData<List<CardModel>>
    fun addObtained(card: CardModel)
    fun removeObtained(card: CardModel)
}
