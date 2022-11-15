package com.ianpedraza.superherocards.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.usecases.GetAllObtainedByRarityUseCase

class FavoritesViewModel(
    private val getAllObtainedByRarityUseCase: GetAllObtainedByRarityUseCase
) : ViewModel() {
    private val _rarity = MutableLiveData<Rarity?>(null)

    val cards: LiveData<List<CardModel>> = Transformations.switchMap(_rarity) { rarity ->
        return@switchMap getAllObtainedByRarityUseCase(rarity)
    }

    fun filterByRarity(rarity: Rarity?) {
        _rarity.value = rarity
    }

    @Suppress("UNCHECKED_CAST")
    class FavoritesViewModelFactory(
        private val getAllObtainedByRarityUseCase: GetAllObtainedByRarityUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (FavoritesViewModel(getAllObtainedByRarityUseCase) as T)
        }
    }
}
