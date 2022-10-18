package com.ianpedraza.superherocards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel

class GetObtainedUseCase(private val repository: CardsRepository) {
    operator fun invoke(): LiveData<List<CardModel>> = repository.getObtained()
}
