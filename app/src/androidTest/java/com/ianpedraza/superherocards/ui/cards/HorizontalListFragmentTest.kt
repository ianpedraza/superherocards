package com.ianpedraza.superherocards.ui.cards

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.GetAtPositionUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@MediumTest
@RunWith(AndroidJUnit4::class)
internal class HorizontalListFragmentTest {

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
    fun clickOn25thCard_openDetails() {
        val scenario = launchFragmentInContainer<HorizontalListFragment>(
            Bundle(),
            R.style.Theme_SuperheroCards
        )

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        val position = 25
        val itemAtPosition = getAtPositionUseCase(position)!!

        Espresso.onView(withId(R.id.recyclerViewCardsList))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position),
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    ViewActions.click()
                )
            )

        Mockito.verify(navController).navigate(
            HorizontalListFragmentDirections.actionHorizontalListFragmentToDetailFragment(
                itemAtPosition
            )
        )
    }
}
