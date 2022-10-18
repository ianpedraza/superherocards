package com.ianpedraza.superherocards.data.datasources

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.domain.models.CardModel

interface CardsDataSource {
    fun getAll(): LiveData<List<CardModel>>
    fun getObtained(): LiveData<List<CardModel>>
    fun addObtained(card: CardModel)
    fun removeObtained(card: CardModel)
}
