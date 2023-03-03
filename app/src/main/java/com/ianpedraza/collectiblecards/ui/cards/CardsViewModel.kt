package com.ianpedraza.collectiblecards.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity
import com.ianpedraza.collectiblecards.usecases.GetAllByRarityUseCase
import com.ianpedraza.collectiblecards.usecases.GetAllCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllByRarityUseCase: GetAllByRarityUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _rarity: MutableLiveData<Rarity?> = savedStateHandle.getLiveData(KEY_RARITY, null)

    val cards: LiveData<List<CardModel>> = Transformations.switchMap(_rarity) { rarity ->
        return@switchMap if (rarity != null) {
            getAllByRarityUseCase(rarity)
        } else {
            getAllCardsUseCase()
        }
    }

    fun filterByRarity(rarity: Rarity?) {
        _rarity.value = rarity
    }

    companion object {
        const val KEY_RARITY = "rarity"
    }
}
