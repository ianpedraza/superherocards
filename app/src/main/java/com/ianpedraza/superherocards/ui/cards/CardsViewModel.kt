package com.ianpedraza.superherocards.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase

class CardsViewModel(private val getAllCardsUseCase: GetAllCardsUseCase) : ViewModel() {

    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>> get() = _cards

    init {
        fetchData()
    }

    private fun fetchData() {
        _cards.value = getAllCardsUseCase()
    }

    @Suppress("UNCHECKED_CAST")
    class CardsViewModelFactory(
        private val getAllCardsUseCase: GetAllCardsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CardsViewModel(getAllCardsUseCase) as T)
        }
    }
}
