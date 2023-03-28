package com.volokhinaleksey.search.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.search.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class DictionaryOfWordsViewModelTest {

    private lateinit var viewModel: DictionaryOfWordsViewModel

    @Mock
    private lateinit var interactor: SearchWordsInteractor<WordsState>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DictionaryOfWordsViewModel(interactor, UnconfinedTestDispatcher())
    }

    @Test
    fun `test calling getWordMeanings() method`() = runTest {
        `when`(interactor.getWordsData(any(), any())).thenReturn(WordsState.Success(listOf()))
        viewModel.getWordMeanings("", true)
        verify(interactor, times(1)).getWordsData(any(), any())
    }

    @Test
    fun `test return data is not null return true`() = runTest {
        `when`(interactor.getWordsData(any(), any())).thenReturn(WordsState.Success(listOf()))
        viewModel.getWordMeanings("", true)
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isNotNull()
    }

    @Test
    fun `check getting word state return loading`() = runTest {
        `when`(interactor.getWordsData(any(), any())).thenReturn(WordsState.Loading)
        viewModel.getWordMeanings("", true)
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isEqualTo(WordsState.Loading)
    }

    @Test
    fun `check getting word state return success`() = runTest {
        `when`(interactor.getWordsData(any(), any())).thenReturn(WordsState.Success(listOf()))
        viewModel.getWordMeanings("", true)
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isEqualTo(WordsState.Success(listOf()))
    }

    @Test
    fun `check getting word state return error`() = runTest {
        `when`(interactor.getWordsData(any(), any()))
            .thenReturn(WordsState.Error(Throwable("Test error")))
        viewModel.getWordMeanings("", true)
        val value: WordsState.Error =
            viewModel.currentData.getOrAwaitValueTest() as WordsState.Error
        assertThat(value.error.message).isEqualTo(Throwable("Test error").message)
    }

}