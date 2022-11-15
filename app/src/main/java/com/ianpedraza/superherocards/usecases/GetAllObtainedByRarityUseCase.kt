package com.ianpedraza.superherocards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity

class GetAllObtainedByRarityUseCase(private val repository: CardsRepository) {
    operator fun invoke(rarity: Rarity?): LiveData<List<CardModel>> =
        repository.getAllObtainedByRarity(rarity)
}
