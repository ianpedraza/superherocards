package com.ianpedraza.collectiblecards.ui.cards

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ianpedraza.collectiblecards.CustomMatches
import com.ianpedraza.collectiblecards.R
import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.data.repositories.DefaultCardsRepository
import com.ianpedraza.collectiblecards.framework.CardsLocalDataSource
import com.ianpedraza.collectiblecards.ui.cards.list.ListFragment
import com.ianpedraza.collectiblecards.ui.cards.list.ListFragmentDirections
import com.ianpedraza.collectiblecards.usecases.GetAtPositionUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@MediumTest
@RunWith(AndroidJUnit4::class)
internal class ListFragmentTest {

    private lateinit var dataSource: CardsDataSource
    private lateinit var repository: DefaultCardsRepository
    private lateinit var getAtPositionUseCase: GetAtPositionUseCase

    @Before
    fun initRepository() {
        dataSource = CardsLocalDataSource
        repository = DefaultCardsRepository(dataSource)
        getAtPositionUseCase = GetAtPositionUseCase(repository)
    }

    @Test
    fun navigateTo25thCard() {
        val scenario = launchFragmentInContainer<ListFragment>(
            Bundle(),
            R.style.Theme_SuperheroCards
        )

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        val position = 25
        val itemAtPosition = getAtPositionUseCase(position)!!

        onView(withId(R.id.recyclerViewCardsList))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
            .check(matches(CustomMatches.withViewAtPosition(position, isDisplayed())))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    click()
                )
            )

        verify(navController).navigate(
            ListFragmentDirections.actionHorizontalListFragmentToDetailFragment(itemAtPosition)
        )
    }
}
