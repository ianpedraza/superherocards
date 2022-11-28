package com.ianpedraza.superherocards.usecases

import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class GetAllCardsUseCase(private val repository: CardsRepository) {
    operator fun invoke(): List<CardModel> = repository.getAll()
}
