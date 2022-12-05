package com.ianpedraza.superherocards.ui.favorites

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.usecases.GetAllObtainedByRarityUseCase

class FavoritesViewModel(
    private val getAllObtainedByRarityUseCase: GetAllObtainedByRarityUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _rarity: MutableLiveData<Rarity?> = savedStateHandle.getLiveData(KEY_RARITY, null)

    val cards: LiveData<List<CardModel>> = Transformations.switchMap(_rarity) { rarity ->
        return@switchMap getAllObtainedByRarityUseCase(rarity)
    }

    fun filterByRarity(rarity: Rarity?) {
        _rarity.value = rarity
    }

    @Suppress("UNCHECKED_CAST")
    class FavoritesViewModelFactory(
        private val getAllObtainedByRarityUseCase: GetAllObtainedByRarityUseCase,
        savedStateRegistryOwner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return (FavoritesViewModel(getAllObtainedByRarityUseCase, handle) as T)
        }
    }

    companion object {
        const val KEY_RARITY = "rarity"
    }
}
