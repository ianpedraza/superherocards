package com.ianpedraza.superherocards.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ianpedraza.superherocards.FakeRepository
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.getOrAwaitValue
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.IsCardObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class DetailViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var card1: CardModel
    private lateinit var card2: CardModel
    private lateinit var card3: CardModel

    private lateinit var fakeData: List<CardModel>

    private lateinit var repository: CardsRepository

    private lateinit var isCardObtainedUseCase: IsCardObtainedUseCase
    private lateinit var addObtainedUseCase: AddObtainedUseCase
    private lateinit var removeObtainedUseCase: RemoveObtainedUseCase

    private lateinit var cardsViewModel: DetailViewModel

    @Before
    fun setupViewModel() {
        card1 = CardModel(name = "Name1", rarity = Rarity.Rarity1)
        card2 = CardModel(name = "Name2", rarity = Rarity.Rarity5)
        card3 = CardModel(name = "Name3", rarity = Rarity.Rarity1)

        fakeData = listOf(card1, card2, card3)

        repository = FakeRepository(fakeData)

        isCardObtainedUseCase = IsCardObtainedUseCase(repository)
        addObtainedUseCase = AddObtainedUseCase(repository)
        removeObtainedUseCase = RemoveObtainedUseCase(repository)

        // cardsViewModel = DetailViewModel(isCardObtainedUseCase, addObtainedUseCase, removeObtainedUseCase)

        cardsViewModel.setCard(card1)
    }

    @Test
    fun `mark a card as obtained`() {
        cardsViewModel.addObtained()

        val isObtained = cardsViewModel.isObtained.getOrAwaitValue()
        assertThat(isObtained, IsEqual(true))
    }
}
