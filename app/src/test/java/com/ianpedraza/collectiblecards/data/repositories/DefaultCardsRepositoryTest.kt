package com.ianpedraza.collectiblecards.data.repositories

import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.framework.CardsLocalDataSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DefaultCardsRepositoryTest {

    private val cardsDataSource: CardsDataSource = mock<CardsLocalDataSource>()
    private lateinit var cardsRepository: CardsRepository

    @Before
    fun setup() {
        cardsRepository = DefaultCardsRepository(cardsDataSource)
    }

    @Test
    fun getAllCards_invokesDataSourceGetAll() {
        cardsRepository.getAll()
        verify(cardsDataSource).getAll()
    }
}
