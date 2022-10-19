package com.ianpedraza.superherocards.usecases

import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class GetAtPositionUseCase(private val repository: CardsRepository) {
    operator fun invoke(position: Int): CardModel? = repository.getAtPosition(position)
}
