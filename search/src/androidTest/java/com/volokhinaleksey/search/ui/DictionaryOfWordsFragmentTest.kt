package com.volokhinaleksey.search.ui

import android.content.ActivityNotFoundException
import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.core.R
import com.volokhinaleksey.search.di.databaseModuleTesting
import com.volokhinaleksey.search.di.dictionaryOfWordsScreenTesting
import com.volokhinaleksey.search.di.networkModuleTesting
import com.volokhinaleksey.search.di.repositoryModuleTesting
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class DictionaryOfWordsFragmentTest : TestCase(), KoinTest {

    private lateinit var scenario: FragmentScenario<DictionaryOfWordsFragment>

    @Before
    fun setUp() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                listOf(
                    databaseModuleTesting,
                    repositoryModuleTesting,
                    networkModuleTesting,
                    dictionaryOfWordsScreenTesting
                )
            )
        }
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_DictionaryOfWords)
    }

    @Test
    fun check_Fragment_NotNull_ReturnTrue() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun check_SearchInput_IsVisible_ReturnTrue() = run {
        SearchScreen {
            searchInput {
                isVisible()
            }
        }
    }

    @Test
    fun check_TypeTextInSearchField_ReturnTrue() = run {
        SearchScreen {
            searchInput {
                edit {
                    clearText()
                    typeText("dictionary")
                    hasText("dictionary")
                }
            }
        }
    }

    @Test
    fun check_SearchingDataInSearchField_ReturnTrue() = run {
        SearchScreen {
            searchInput {
                edit {
                    typeText("words")
                    pressImeAction()
                }
            }
        }
    }

    @Test
    fun check_wordsList_scrollTo_ReturnTrue() = run {
        SearchScreen {
            searchInput {
                edit {
                    typeText("book")
                    pressImeAction()
                }
            }
            wordsList {
                scrollTo(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("bookseller")
                    )
                )
            }
        }
    }


    @Test
    fun check_wordsList_IsVisible_ReturnTrue() = run {
        SearchScreen {
            wordsList {
                isVisible()
            }
        }
    }

    @Test
    fun check_OfflineMessage_IsGone_ReturnTrue() = run {
        SearchScreen {
            offlineMessageView {
                isGone()
            }
        }
    }

//    @Test
//    fun check_OfflineMessage_IsVisible_ReturnTrue() {
//        before {
//            tryToggleNetwork(shouldEnable = true)
//        }.after {
//            tryToggleNetwork(shouldEnable = true)
//        }.run {
//            tryToggleNetwork(false)
//            SearchScreen {
//                offlineMessageView {
//                    isVisible()
//                }
//            }
//        }
//    }

    private fun tryToggleNetwork(shouldEnable: Boolean) {
        try {
            if (shouldEnable) {
                device.network.enable()
            } else {
                device.network.disable()
            }
        } catch (ex: ActivityNotFoundException) { // There's no WIFI activity on AVD with API < 25
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) return
            throw ex
        }
    }

    @After
    fun tearDown() {
        scenario.close()
        stopKoin()
    }
}