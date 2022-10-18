package com.ianpedraza.superherocards.usecases

import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.data.repositories.CardsRepository

class RemoveFavoriteUseCase(private val repository: CardsRepository) {
    operator fun invoke(card: CardModel): Unit = repository.removeFavorite(card)
}
