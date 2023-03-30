package com.volokhinaleksey.description.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.core.R
import com.volokhinaleksey.core.ui.DATA_KEY
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.description.di.databaseModuleTesting
import com.volokhinaleksey.description.di.networkModuleTesting
import com.volokhinaleksey.description.di.repositoryModuleTesting
import com.volokhinaleksey.description.di.wordDescriptionScreen
import com.volokhinaleksey.models.local.WordEntity
import com.volokhinaleksey.models.ui.Definition
import com.volokhinaleksey.models.ui.Meaning
import com.volokhinaleksey.models.ui.Translation
import com.volokhinaleksey.models.ui.Word
import io.github.kakaocup.kakao.common.utilities.getResourceString
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

class WordDescriptionFragmentTest : TestCase(), KoinTest {

    private lateinit var scenario: FragmentScenario<WordDescriptionFragment>
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
                    wordDescriptionScreen
                )
            )
        }
        val word = Word(
            id = 217, word = "word",
            meanings =
            listOf(
                Meaning(
                    id = 92160, translation = Translation(translation = "слово"), imageUrl = "",
                    wordId = 0, word = "", transcription = "wɜːd",
                    definition = Definition(wordDefinition = ""), examples = listOf(),
                    similarTranslation = listOf()
                )
            )
        )
        scenario =
            launchFragmentInContainer(
                themeResId = R.style.Theme_DictionaryOfWords, fragmentArgs = bundleOf(
                    DATA_KEY to word
                )
            )
        runTest {
            db.wordDao().insert(WordEntity(word = word.word, id = word.id))
        }
    }

    @Test
    fun fragment_IsNotNull_ReturnTrue() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun fragment_HasArguments_ReturnTrue() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        WordDescription {
            word {
                hasText("word")
            }
        }
    }

    @Test
    fun wordTextView_IsVisible_ReturnTrue() {
        WordDescription { word { isVisible() } }
    }

    @Test
    fun backArrowIcon_IsVisible_ReturnTrue() {
        WordDescription { backArrowIcon { isVisible() } }
    }

    @Test
    fun textSpeechIcon_IsVisible_ReturnTrue() {
        WordDescription { textSpeechIcon { isVisible() } }
    }

    @Test
    fun favoriteCheckBox_IsVisible_ReturnTrue() {
        WordDescription { favoriteCheckBox { isVisible() } }
    }

    @Test
    fun transcription_IsVisible_ReturnTrue() {
        WordDescription { transcription { isVisible() } }
    }

    @Test
    fun translation_IsVisible_ReturnTrue() {
        WordDescription { translation { isVisible() } }
    }

    @Test
    fun partOfSpeechAbbreviation_IsVisible_ReturnTrue() {
        WordDescription { partOfSpeechAbbreviation { isVisible() } }
    }

    @Test
    fun similarTranslationLabel_IsVisible_ReturnTrue() {
        WordDescription { similarTranslationLabel { isVisible() } }
    }

    @Test
    fun similarTranslationLabel_HasText_ReturnTrue() {
        WordDescription { similarTranslationLabel { hasText(getResourceString(R.string.similar_translation)) } }
    }

    @Test
    fun similarTranslation_IsVisible_ReturnTrue() {
        WordDescription { similarTranslation { isVisible() } }
    }

    @Test
    fun examplesLabel_IsVisible_ReturnTrue() {
        WordDescription { examplesLabel { isVisible() } }
    }

    @Test
    fun examplesLabel_HasText_ReturnTrue() {
        WordDescription { examplesLabel { hasText(getResourceString(R.string.examples)) } }
    }

    @Test
    fun examplesList_IsVisible_ReturnTrue() {
        WordDescription { examplesList { isVisible() } }
    }

    @Test
    fun reloadButton_IsGone_ReturnTrue() {
        WordDescription { reloadButton { isGone() } }
    }

    @Test
    fun errorMessage_IsGone_ReturnTrue() {
        WordDescription { errorMessage { isGone() } }
    }

    @Test
    fun favoriteCheckBox_isActivated_ReturnTrue() {
        WordDescription {
            favoriteCheckBox {
                setChecked(false)
                click()
                isChecked()
            }
        }
    }

    @Test
    fun backArrowIcon_onClick_ReturnTrue() {
        WordDescription {
            backArrowIcon {
                isClickable()
            }
        }
    }

    @Test
    fun textSpeechIcon_onClick_ReturnTrue() {
        WordDescription {
            textSpeechIcon {
                click()
            }
        }
    }

    @Test
    fun similarTranslation_ScrollTo_ReturnTrue() {
        WordDescription {
            similarTranslation {
                scrollTo(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("обещание")
                    )
                )
            }
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        scenario.close()
    }
}