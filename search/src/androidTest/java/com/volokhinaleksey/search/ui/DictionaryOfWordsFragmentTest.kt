package com.volokhinaleksey.search.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.volokhinaleksey.search.R
import com.volokhinaleksey.search.ui.adapter.DictionaryOfWordsAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
class DictionaryOfWordsFragmentTest : KoinTest {

    @Test
    fun navigateToDescriptionFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInContainer<DictionaryOfWordsFragment>().onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.words_list))
            .perform(actionOnItemAtPosition<DictionaryOfWordsAdapter.ViewHolder>(0, click()))

        verify(navController).navigate(com.volokhinaleksey.core.R.id.description_nav_graph)
    }

}