package com.ianpedraza.superherocards.data.datasources

import com.ianpedraza.superherocards.domain.models.CardModel

interface CardsDataSource {
    fun getAll(): List<CardModel>
    fun getAtPosition(position: Int): CardModel?
}
