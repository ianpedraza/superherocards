package com.ianpedraza.superherocards.ui.cards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ianpedraza.superherocards.FakeRepository
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.getOrAwaitValue
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CardsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var card1: CardModel
    private lateinit var card2: CardModel
    private lateinit var card3: CardModel
    private lateinit var card4: CardModel
    private lateinit var card5: CardModel

    private lateinit var fakeData: List<CardModel>

    private lateinit var repository: CardsRepository

    private lateinit var getAllCardsUseCase: GetAllCardsUseCase
    private lateinit var getAllByRarityUseCase: GetAllByRarityUseCase

    private lateinit var cardsViewModel: CardsViewModel

    @Before
    fun setupViewModel() {
        card1 = CardModel(name = "Name1", rarity = Rarity.Rarity1)
        card2 = CardModel(name = "Name2", rarity = Rarity.Rarity5)
        card3 = CardModel(name = "Name3", rarity = Rarity.Rarity1)
        card4 = CardModel(name = "Name4", rarity = Rarity.Rarity5)
        card5 = CardModel(name = "Name5", rarity = Rarity.Rarity1)

        fakeData = listOf(card1, card2, card3, card4, card5)

        repository = FakeRepository(fakeData)

        getAllCardsUseCase = GetAllCardsUseCase(repository)
        getAllByRarityUseCase = GetAllByRarityUseCase(repository)

        // cardsViewModel = CardsViewModel(getAllCardsUseCase, getAllByRarityUseCase)
    }

    @Test
    fun `given a level of rarity, filter only that rarity type`() {
        val rarity = Rarity.Rarity5
        val expectedResult = listOf(card2, card4)

        cardsViewModel.filterByRarity(rarity)

        val cards = cardsViewModel.cards.getOrAwaitValue()
        assertThat(cards, IsEqual(expectedResult))
    }
}
