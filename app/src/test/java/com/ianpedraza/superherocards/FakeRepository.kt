package com.ianpedraza.superherocards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity

class FakeRepository(private val data: List<CardModel>) : CardsRepository {

    private val obtained: MutableLiveData<List<CardModel>> = MutableLiveData(emptyList())

    override fun getAll(): LiveData<List<CardModel>> {
        return MutableLiveData(data)
    }

    override fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>> {
        val filtered = data.filter { card -> card.rarity == rarity }
        return MutableLiveData(filtered)
    }

    override fun getAllObtainedByRarity(rarity: Rarity?): LiveData<List<CardModel>> {
        return obtained
    }

    override fun getAtPosition(position: Int): CardModel {
        return data[position]
    }

    override fun getAllObtained(): LiveData<List<CardModel>> {
        return obtained
    }

    override fun isCardObtained(card: CardModel): LiveData<Boolean> {
        return Transformations.map(obtained) { obtainedList ->
            obtainedList.contains(card)
        }
    }

    override fun addObtained(card: CardModel) {
        obtained.value = obtained.value?.toMutableList()?.apply {
            add(card)
        }
    }

    override fun removeObtained(card: CardModel) {
        obtained.value = obtained.value?.toMutableList()?.apply {
            remove(card)
        }
    }
}
