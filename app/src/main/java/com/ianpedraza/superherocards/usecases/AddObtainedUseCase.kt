package com.ianpedraza.superherocards.usecases

import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class AddObtainedUseCase(private val repository: CardsRepository) {
    operator fun invoke(card: CardModel): Unit = repository.addObtained(card)
}
