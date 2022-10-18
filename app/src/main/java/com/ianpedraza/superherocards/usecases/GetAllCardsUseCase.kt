package com.ianpedraza.superherocards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.data.repositories.CardsRepository

class GetAllCardsUseCase(private val repository: CardsRepository) {
    operator fun invoke(): LiveData<List<CardModel>> = repository.getAll()
}
