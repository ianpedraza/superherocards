package com.ianpedraza.superherocards.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ianpedraza.superherocards.CustomMatches
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.data.datasources.CardsDataSource
import com.ianpedraza.superherocards.data.repositories.DefaultCardsRepository
import com.ianpedraza.superherocards.framework.CardsLocalDataSource
import com.ianpedraza.superherocards.usecases.GetAtPositionUseCase
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

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
    fun scrollTo25thCard_goToDetail_performAddToFav() {
        val position = 25

        onView(withId(R.id.recyclerViewCardsList))
            .perform(
                scrollToPosition<RecyclerView.ViewHolder>(position)
            )

        onView(withId(R.id.recyclerViewCardsList))
            .check(
                matches(
                    CustomMatches.withViewAtPosition(
                        position,
                        isDisplayed()
                    )
                )
            )

        onView(withId(R.id.recyclerViewCardsList))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    click()
                )
            )

        onView(withId(R.id.fabDetailObtained))
            .check(matches(isDisplayed()))

        onView(withId(R.id.fabDetailObtained))
            .perform(click())

        onView(withId(R.id.fabDetailObtained))
            .check(
                matches(
                    withTagValue(equalTo(R.drawable.ic_checked))
                )
            )
    }
}
