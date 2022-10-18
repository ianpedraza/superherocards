package com.ianpedraza.superherocards.ui.cards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.getOrAwaitValue
import com.ianpedraza.superherocards.usecases.GetAllByRarityUseCase
import com.ianpedraza.superherocards.usecases.GetAllCardsUseCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CardsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataSource: CardsDataSource
    private lateinit var repository: CardsRepository

    private lateinit var getAllCardsUseCase: GetAllCardsUseCase
    private lateinit var getAllByRarityUseCase: GetAllByRarityUseCase

    private lateinit var cardsViewModel: CardsViewModel

    @Before
    fun setupViewModel() {
        dataSource = CardsLocalDataSource
        repository = CardsRepository(dataSource)

        getAllCardsUseCase = GetAllCardsUseCase(repository)
        getAllByRarityUseCase = GetAllByRarityUseCase(repository)

        cardsViewModel = CardsViewModel(getAllCardsUseCase, getAllByRarityUseCase)
    }

    @Test
    fun `given a level of rarity, filter only that rarity type`() {
        val rarity = Rarity.Rarity5
        cardsViewModel.filterByRarity(rarity)

        val cards = cardsViewModel.cards.getOrAwaitValue()
        assertThat(cards.all { it.rarity == rarity }, `is`(true))
    }
}
