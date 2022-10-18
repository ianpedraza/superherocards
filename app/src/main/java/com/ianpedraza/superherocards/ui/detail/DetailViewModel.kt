package com.ianpedraza.superherocards.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase

class DetailViewModel(
    private val getObtainedUseCase: GetObtainedUseCase,
    private val addObtainedUseCase: AddObtainedUseCase,
    private val removeObtainedUseCase: RemoveObtainedUseCase
) : ViewModel() {

    private val _card = MutableLiveData<CardModel>()

    val isObtained: LiveData<Boolean> =
        Transformations.map(getObtainedUseCase()) { obtained ->
            _card.value != null && obtained.contains(_card.value)
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

    fun removeObtained() {
        _card.value?.let { card ->
            removeObtainedUseCase(card)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class DetailViewModelFactory(
        private val getObtainedUseCase: GetObtainedUseCase,
        private val addObtainedUseCase: AddObtainedUseCase,
        private val removeObtainedUseCase: RemoveObtainedUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (
                DetailViewModel(
                    getObtainedUseCase,
                    addObtainedUseCase,
                    removeObtainedUseCase
                ) as T
                )
        }
    }
}
