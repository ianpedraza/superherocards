package com.ianpedraza.collectiblecards.usecases

import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetAllCardsUseCaseTest {

    private val cardsRepository: CardsRepository = mock()

    private lateinit var getAllCardsUseCase: GetAllCardsUseCase

    @Before
    fun setup() {
        getAllCardsUseCase = GetAllCardsUseCase(cardsRepository)
    }

    @Test
    fun invokeUseCase_callsRepository() {
        getAllCardsUseCase()
        verify(cardsRepository).getAll()
    }
}
