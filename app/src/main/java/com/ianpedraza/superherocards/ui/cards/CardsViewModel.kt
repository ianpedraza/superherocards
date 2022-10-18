package com.ianpedraza.superherocards.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase

class CardsViewModel(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllByRarityUseCase: GetAllByRarityUseCase
) : ViewModel() {

    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>> get() = _cards

    init {
        fetchData()
    }

    fun fetchData() {
        _cards.value = getAllCardsUseCase().value
    }

    fun filterByRarity(rarity: Rarity) {
        _cards.value = getAllByRarityUseCase(rarity).value
    }

    @Suppress("UNCHECKED_CAST")
    class CardsViewModelFactory(
        private val getAllCardsUseCase: GetAllCardsUseCase,
        private val getAllByRarityUseCase: GetAllByRarityUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CardsViewModel(getAllCardsUseCase, getAllByRarityUseCase) as T)
        }
    }
}
