package com.ianpedraza.superherocards.data.datasources

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.domain.models.CardModel

interface CardsDataSource {
    fun getAll(): LiveData<List<CardModel>>
    fun getFavorites(): LiveData<List<CardModel>>
    fun addFavorite(card: CardModel)
    fun removeFavorite(card: CardModel)
}
