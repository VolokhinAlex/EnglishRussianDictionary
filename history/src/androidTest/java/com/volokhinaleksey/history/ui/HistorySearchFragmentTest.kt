package com.volokhinaleksey.history.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.core.R
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.history.di.databaseModuleTesting
import com.volokhinaleksey.history.di.historyScreen
import com.volokhinaleksey.history.di.networkModuleTesting
import com.volokhinaleksey.history.di.repositoryModuleTesting
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.local.WordEntity
import com.volokhinaleksey.models.ui.Word
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class HistorySearchFragmentTest : TestCase(), KoinTest {

    private lateinit var scenario: FragmentScenario<HistorySearchFragment>
    private val db: DictionaryDatabase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                listOf(
                    databaseModuleTesting,
                    repositoryModuleTesting,
                    networkModuleTesting,
                    historyScreen
                )
            )
        }
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_DictionaryOfWords)
        val word = Word(id = 217, word = "word", meanings = listOf())
        runTest {
            db.wordDao().insert(WordEntity(word = word.word, id = word.id))
            db.historyDao()
                .insert(HistoryEntity(word = word.word, wordId = word.id))
        }
    }

    @Test
    fun fragment_IsNotNull_ReturnTrue() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun historyWordsList_IsNotNull_ReturnTrue() {
        scenario.onFragment {
            val list = it.requireActivity()
                .findViewById<RecyclerView>(com.volokhinaleksey.history.R.id.history_words_list)
            assertThat(list).isNotNull()
        }
    }

    @Test
    fun historyWordsList_IsVisible_ReturnTrue() {
        HistoryScreen {
            historyWords { isVisible() }
        }
    }

    @Test
    fun historyWordsList_ScrollTo_ReturnTrue() {
        HistoryScreen {
            historyWords {
                scrollTo(ViewMatchers.hasDescendant(ViewMatchers.withText("word")))
            }
        }
    }

    @Test
    fun historyWordsList_ClickOnItem_ReturnTrue() {
        HistoryScreen {
            historyWords {
                scrollTo(0)
                click()
            }
        }
    }

    @Test
    fun errorMessageField_IsGone_ReturnTrue() {
        HistoryScreen {
            errorMessage {
                isGone()
            }
        }
    }

    @Test
    fun reloadButton_IsGone_ReturnTrue() {
        HistoryScreen {
            reloadButton { isGone() }
        }
    }

    @After
    fun tearDown() {
        scenario.close()
        stopKoin()
    }
}