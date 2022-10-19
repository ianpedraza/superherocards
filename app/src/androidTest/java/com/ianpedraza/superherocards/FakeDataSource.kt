package com.ianpedraza.superherocards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity

class FakeDataSource(private val data: List<CardModel>) : CardsDataSource {

    private val obtained: MutableLiveData<List<CardModel>> = MutableLiveData(emptyList())

    override fun getAll(): LiveData<List<CardModel>> = MutableLiveData(data)

    override fun getAllByRarity(rarity: Rarity): LiveData<List<CardModel>> {
        val filtered = data.filter { card -> card.rarity == rarity }
        return MutableLiveData(filtered)
    }

    override fun getAtPosition(position: Int): CardModel? = data[position]

    override fun getAllObtained(): LiveData<List<CardModel>> = obtained

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
