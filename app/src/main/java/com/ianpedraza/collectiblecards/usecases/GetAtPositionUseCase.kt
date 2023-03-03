package com.ianpedraza.collectiblecards.usecases

import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import com.ianpedraza.collectiblecards.domain.models.CardModel
import javax.inject.Inject

class GetAtPositionUseCase @Inject constructor(private val repository: CardsRepository) {
    operator fun invoke(position: Int): CardModel? = repository.getAtPosition(position)
}
