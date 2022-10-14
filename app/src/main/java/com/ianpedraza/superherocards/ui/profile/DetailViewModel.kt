package com.ianpedraza.superherocards.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ianpedraza.superherocards.data.CardModel
import com.ianpedraza.superherocards.data.CardsDummyData

class DetailViewModel : ViewModel() {

    private val _card = MutableLiveData<CardModel>()

    val isFavorite: LiveData<Boolean> =
        Transformations.map(CardsDummyData.getFavorites()) { favorites ->
            _card.value != null && favorites.contains(_card.value)
        }

    fun setCard(card: CardModel) {
        _card.value = card
    }

    fun toggleFavorite() {
        if (isFavorite.value == true) {
            removeFavorite()
        } else {
            addFavorite()
        }
    }

    private fun addFavorite() {
        _card.value?.let { card ->
            CardsDummyData.addFavorite(card)
        }
    }

    private fun removeFavorite() {
        _card.value?.let { card ->
            CardsDummyData.removeFavorite(card)
        }
    }
}
