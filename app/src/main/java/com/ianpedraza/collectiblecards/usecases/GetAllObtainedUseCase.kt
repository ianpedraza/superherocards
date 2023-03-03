package com.ianpedraza.collectiblecards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import com.ianpedraza.collectiblecards.domain.models.CardModel
import javax.inject.Inject

class GetAllObtainedUseCase @Inject constructor(private val repository: CardsRepository) {
    operator fun invoke(): LiveData<List<CardModel>> = repository.getAllObtained()
}
