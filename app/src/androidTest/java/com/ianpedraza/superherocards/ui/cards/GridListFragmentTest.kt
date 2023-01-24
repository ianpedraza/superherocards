package com.ianpedraza.superherocards.ui.cards

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
import androidx.test.filters.LargeTest
import com.ianpedraza.superherocards.CustomMatches
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.ui.cards.grid.GridListFragment
import com.ianpedraza.superherocards.ui.cards.grid.GridListFragmentDirections
import com.ianpedraza.superherocards.usecases.GetAtPositionUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class GridListFragmentTest {

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
        val scenario =
            launchFragmentInContainer<GridListFragment>(Bundle(), R.style.Theme_SuperheroCards)

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        val position = 25
        val itemAtPosition = getAtPositionUseCase(position)!!

        onView(withId(R.id.recyclerViewCardsGrid))
            .perform(
                scrollToPosition<RecyclerView.ViewHolder>(position)
            )

        onView(withId(R.id.recyclerViewCardsGrid))
            .check(matches(CustomMatches.withViewAtPosition(position, isDisplayed())))

        onView(withId(R.id.recyclerViewCardsGrid))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    click()
                )
            )

        verify(navController)
            .navigate(
                GridListFragmentDirections.actionGridListFragmentToDetailFragment(
                    itemAtPosition
                )
            )

        // onView(withId(R.id.imageViewDetailCover))
        // .check(matches(isDisplayed()))
    }
}
