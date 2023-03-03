package com.ianpedraza.collectiblecards.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ianpedraza.collectiblecards.R
import com.ianpedraza.collectiblecards.domain.models.CardModel
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
internal class DetailFragmentTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var card: CardModel

    @Before
    fun initRepository() {
        card = CardModel(name = "Name1")
    }

    @Test
    fun addCardToObtained() {
        launchFragmentInContainer<DetailFragment>(
            bundleOf("card" to card),
            R.style.Theme_SuperheroCards
        )

        Espresso.onView(ViewMatchers.withId(R.id.fabDetailObtained)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.fabDetailObtained))
            .check(matches(ViewMatchers.withTagValue(equalTo(R.drawable.ic_checked))))
    }
}
