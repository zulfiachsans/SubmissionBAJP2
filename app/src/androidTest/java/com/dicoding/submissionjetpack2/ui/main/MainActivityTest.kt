package com.dicoding.submissionjetpack2.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.IsNot.not
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.submissionjetpack2.R
import com.dicoding.submissionjetpack2.utils.DataDummy
import com.dicoding.submissionjetpack2.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setupAll() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadAll() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        onView(withText("TVSHOWS")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
              click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail)).check(matches(not(withTagValue(equalTo("")))))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_movie)).check(matches(not(withTagValue(equalTo("")))))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(not(withContentDescription(""))))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(not(withText(""))))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TVSHOWS")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail)).check(matches(not(withTagValue(equalTo("")))))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_movie)).check(matches(not(withTagValue(equalTo("")))))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(not(withContentDescription(""))))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(not(withText(""))))
    }

}