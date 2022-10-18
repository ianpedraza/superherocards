package com.ianpedraza.superherocards.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.CardsRepository
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.getOrAwaitValue
import com.ianpedraza.superherocards.usecases.AddObtainedUseCase
import com.ianpedraza.superherocards.usecases.GetObtainedUseCase
import com.ianpedraza.superherocards.usecases.RemoveObtainedUseCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class DetailViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataSource: CardsDataSource
    private lateinit var repository: CardsRepository

    private lateinit var getObtainedUseCase: GetObtainedUseCase
    private lateinit var addObtainedUseCase: AddObtainedUseCase
    private lateinit var removeObtainedUseCase: RemoveObtainedUseCase

    private lateinit var cardsViewModel: DetailViewModel

    @Before
    fun setupViewModel() {
        dataSource = CardsLocalDataSource
        repository = CardsRepository(dataSource)

        getObtainedUseCase = GetObtainedUseCase(repository)
        addObtainedUseCase = AddObtainedUseCase(repository)
        removeObtainedUseCase = RemoveObtainedUseCase(repository)

        cardsViewModel =
            DetailViewModel(getObtainedUseCase, addObtainedUseCase, removeObtainedUseCase)

        val card = CardModel(name = "Name1", image = "Image1", description = "Description1")
        cardsViewModel.setCard(card)
    }

    @Test
    fun `mark a card as obtained`() {
        cardsViewModel.addObtained()
        assertThat(cardsViewModel.isObtained.getOrAwaitValue(), `is`(true))
    }
}
