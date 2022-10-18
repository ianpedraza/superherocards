package com.ianpedraza.superherocards.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.usecases.AddFavoriteUseCase
import com.ianpedraza.superherocards.usecases.GetFavoritesUseCase
import com.ianpedraza.superherocards.usecases.RemoveFavoriteUseCase

class DetailViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoritesUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    private val _card = MutableLiveData<CardModel>()

    val isFavorite: LiveData<Boolean> =
        Transformations.map(getFavoritesUseCase()) { favorites ->
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
            addFavoritesUseCase(card)
        }
    }

    private fun removeFavorite() {
        _card.value?.let { card ->
            removeFavoriteUseCase(card)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class DetailViewModelFactory(
        private val getFavoritesUseCase: GetFavoritesUseCase,
        private val addFavoritesUseCase: AddFavoriteUseCase,
        private val removeFavoriteUseCase: RemoveFavoriteUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (
                DetailViewModel(
                    getFavoritesUseCase,
                    addFavoritesUseCase,
                    removeFavoriteUseCase
                ) as T
                )
        }
    }
}
