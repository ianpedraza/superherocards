package com.ianpedraza.superherocards.ui.detail

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.IsCardObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase

class DetailViewModel(
    private val isCardObtainedUseCase: IsCardObtainedUseCase,
    private val addObtainedUseCase: AddObtainedUseCase,
    private val removeObtainedUseCase: RemoveObtainedUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _card: MutableLiveData<CardModel> = savedStateHandle.getLiveData(KEY_CARD)

    val isObtained: LiveData<Boolean> =
        Transformations.switchMap(_card) { card ->
            return@switchMap if (card == null) {
                MutableLiveData(false)
            } else {
                isCardObtainedUseCase(card)
            }
        }

    fun setCard(card: CardModel) {
        _card.value = card
    }

    fun toggleObtained() {
        if (isObtained.value == true) {
            removeObtained()
        } else {
            addObtained()
        }
    }

    fun addObtained() {
        _card.value?.let { card ->
            addObtainedUseCase(card)
        }
    }

    private fun removeObtained() {
        _card.value?.let { card ->
            removeObtainedUseCase(card)
        }
    }

    companion object {
        const val KEY_CARD = "card"
    }

    @Suppress("UNCHECKED_CAST")
    class DetailViewModelFactory(
        private val isCardObtainedUseCase: IsCardObtainedUseCase,
        private val addObtainedUseCase: AddObtainedUseCase,
        private val removeObtainedUseCase: RemoveObtainedUseCase,
        savedStateRegistryOwner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return (
                DetailViewModel(
                    isCardObtainedUseCase,
                    addObtainedUseCase,
                    removeObtainedUseCase,
                    handle
                ) as T
                )
        }
    }
}
