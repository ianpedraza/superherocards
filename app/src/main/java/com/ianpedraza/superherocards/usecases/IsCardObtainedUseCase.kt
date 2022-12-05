package com.ianpedraza.superherocards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class IsCardObtainedUseCase(private val repository: CardsRepository) {
    operator fun invoke(card: CardModel): LiveData<Boolean> = repository.isCardObtained(card)
}
