package com.ianpedraza.collectiblecards.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.usecases.AddObtainedUseCase
import com.ianpedraza.collectiblecards.usecases.IsCardObtainedUseCase
import com.ianpedraza.collectiblecards.usecases.RemoveObtainedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val isCardObtainedUseCase: IsCardObtainedUseCase,
    private val addObtainedUseCase: AddObtainedUseCase,
    private val removeObtainedUseCase: RemoveObtainedUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _card: MutableLiveData<CardModel> = savedStateHandle.getLiveData(KEY_CARD)

    private var _isObtained: MutableLiveData<Boolean> =
        savedStateHandle.getLiveData(KEY_IS_OBTAINED)

    val isObtained: LiveData<Boolean> get() = _isObtained
    fun setCard(card: CardModel) {
        _card.value = card
        _isObtained = isCardObtainedUseCase(card) as MutableLiveData<Boolean>
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
        const val KEY_IS_OBTAINED = "isObtained"
    }
}
