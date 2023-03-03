package com.ianpedraza.collectiblecards.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.framework.CardsLocalDataSource
import com.ianpedraza.collectiblecards.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CardsLocalDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cardsLocalDataSource: CardsDataSource

    @Before
    fun setupViewModel() {
        cardsLocalDataSource = CardsLocalDataSource
    }

    @Test
    fun `mark a card as obtained`() {
        val card = cardsLocalDataSource.getAtPosition(0)!!
        val expectedResult = listOf(card)

        cardsLocalDataSource.addObtained(card)

        val fieldObtained =
            cardsLocalDataSource::class.java.getDeclaredField("obtained").apply {
                isAccessible = true
            }

        val obtainedList =
            (fieldObtained.get(cardsLocalDataSource) as LiveData<*>).getOrAwaitValue()

        assertThat(expectedResult, IsEqual(obtainedList))
    }
}
