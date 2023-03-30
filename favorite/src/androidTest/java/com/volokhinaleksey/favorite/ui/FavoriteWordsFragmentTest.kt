package com.volokhinaleksey.favorite.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.core.R
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.favorite.di.databaseModuleTesting
import com.volokhinaleksey.favorite.di.favoriteScreen
import com.volokhinaleksey.favorite.di.networkModuleTesting
import com.volokhinaleksey.favorite.di.repositoryModuleTesting
import com.volokhinaleksey.models.local.FavoriteEntity
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

class FavoriteWordsFragmentTest : TestCase(), KoinTest {

    private lateinit var scenario: FragmentScenario<FavoriteWordsFragment>
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
                    favoriteScreen
                )
            )
        }
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_DictionaryOfWords)
        val word = Word(id = 217, word = "word", meanings = listOf())
        runTest {
            db.wordDao().insert(WordEntity(word = word.word, id = word.id))
            db.favoriteDao()
                .insert(FavoriteEntity(wordId = word.id, isFavorite = true, word = word.word))
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
                .findViewById<RecyclerView>(com.volokhinaleksey.favorite.R.id.favorite_words_list)
            assertThat(list).isNotNull()
        }
    }

    @Test
    fun historyWordsList_IsVisible_ReturnTrue() {
        FavoriteScreen {
            favoriteWords { isVisible() }
        }
    }

    @Test
    fun historyWordsList_ClickOnItem_ReturnTrue() {
        FavoriteScreen {
            favoriteWords {
                scrollTo(0)
                click()
            }
        }
    }

    @Test
    fun historyWordsList_SwipeItem_ReturnTrue() {
        FavoriteScreen {
            favoriteWords {
                scrollTo(0)
                swipeLeft()
            }
        }
    }

    @Test
    fun historyWordsList_ScrollTo_ReturnTrue() {
        FavoriteScreen {
            favoriteWords {
                scrollTo(ViewMatchers.hasDescendant(ViewMatchers.withText("word")))
            }
        }
    }

    @Test
    fun errorMessageField_IsGone_ReturnTrue() {
        FavoriteScreen {
            errorMessage {
                isGone()
            }
        }
    }

    @Test
    fun reloadButton_IsGone_ReturnTrue() {
        FavoriteScreen {
            reloadButton { isGone() }
        }
    }

    @After
    fun tearDown() {
        scenario.close()
        stopKoin()
    }
}