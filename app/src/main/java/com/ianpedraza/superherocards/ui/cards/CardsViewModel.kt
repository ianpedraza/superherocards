package com.ianpedraza.superherocards.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ianpedraza.superherocards.data.CardModel
import com.ianpedraza.superherocards.data.CardsDummyData

class CardsViewModel : ViewModel() {

    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>> get() = _cards

    init {
        fetchData()
    }

    private fun fetchData() {
        _cards.value = CardsDummyData.getAll()
    }
}
