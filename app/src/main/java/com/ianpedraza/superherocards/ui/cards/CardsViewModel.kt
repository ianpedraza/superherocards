package com.ianpedraza.superherocards.ui.cards

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase

class CardsViewModel(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllByRarityUseCase: GetAllByRarityUseCase,
    savedStateHandle: SavedStateHandle
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

    @Suppress("UNCHECKED_CAST")
    class CardsViewModelFactory(
        private val getAllCardsUseCase: GetAllCardsUseCase,
        private val getAllByRarityUseCase: GetAllByRarityUseCase,
        savedStateRegistryOwner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return (CardsViewModel(getAllCardsUseCase, getAllByRarityUseCase, handle) as T)
        }
    }
}
