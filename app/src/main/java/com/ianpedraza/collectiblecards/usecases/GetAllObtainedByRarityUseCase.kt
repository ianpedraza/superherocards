package com.ianpedraza.collectiblecards.usecases

import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity
import javax.inject.Inject

class GetAllObtainedByRarityUseCase @Inject constructor(private val repository: CardsRepository) {
    operator fun invoke(rarity: Rarity?): LiveData<List<CardModel>> =
        repository.getAllObtainedByRarity(rarity)
}
