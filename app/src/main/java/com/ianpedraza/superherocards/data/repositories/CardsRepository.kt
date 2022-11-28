package com.ianpedraza.superherocards.data.repositories

import com.ianpedraza.superherocards.domain.models.CardModel

interface CardsRepository {
    fun getAll(): List<CardModel>
    fun getAtPosition(position: Int): CardModel?
}
