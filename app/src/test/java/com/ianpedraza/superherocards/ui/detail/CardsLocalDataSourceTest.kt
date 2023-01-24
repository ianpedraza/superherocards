package com.ianpedraza.superherocards.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.getOrAwaitValue
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

        cardsLocalDataSource.addObtained(card)

        val isObtained = cardsLocalDataSource.isCardObtained(card).getOrAwaitValue()

        assertThat(isObtained, IsEqual(true))
    }
}
