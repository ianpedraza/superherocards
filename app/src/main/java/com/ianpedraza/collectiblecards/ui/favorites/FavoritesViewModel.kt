package com.ianpedraza.collectiblecards.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.domain.models.Rarity
import com.ianpedraza.collectiblecards.usecases.GetAllObtainedByRarityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllObtainedByRarityUseCase: GetAllObtainedByRarityUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _rarity: MutableLiveData<Rarity?> = savedStateHandle.getLiveData(KEY_RARITY, null)

    val cards: LiveData<List<CardModel>> = Transformations.switchMap(_rarity) { rarity ->
        return@switchMap getAllObtainedByRarityUseCase(rarity)
    }

    fun filterByRarity(rarity: Rarity?) {
        _rarity.value = rarity
    }

    companion object {
        const val KEY_RARITY = "rarity"
    }
}
