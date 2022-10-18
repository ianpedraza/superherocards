package com.ianpedraza.superherocards.usecases

import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class RemoveObtainedUseCase(private val repository: CardsRepository) {
    operator fun invoke(card: CardModel): Unit = repository.removeObtained(card)
}
