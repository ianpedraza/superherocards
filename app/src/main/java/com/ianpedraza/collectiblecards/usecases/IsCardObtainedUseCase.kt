package com.ianpedraza.collectiblecards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import com.ianpedraza.collectiblecards.domain.models.CardModel
import javax.inject.Inject

class IsCardObtainedUseCase @Inject constructor(private val repository: CardsRepository) {
    operator fun invoke(card: CardModel): LiveData<Boolean> = repository.isCardObtained(card)
}
