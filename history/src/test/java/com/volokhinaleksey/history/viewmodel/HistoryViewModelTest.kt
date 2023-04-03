package com.volokhinaleksey.history.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.history.getOrAwaitValueTest
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class HistoryViewModelTest {

    private lateinit var viewModel: HistoryViewModel

    @Mock
    private lateinit var interactor: HistoryInteractor<WordsState>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test calling getWordMeanings() method`() = runTest {
        `when`(interactor.getHistoryData()).thenReturn(WordsState.Success(listOf()))
         viewModel = HistoryViewModel(interactor, UnconfinedTestDispatcher())
        verify(interactor, atLeastOnce()).getHistoryData()
    }

    @Test
    fun `test return data is not null return true`() = runTest {
        `when`(interactor.getHistoryData()).thenReturn(WordsState.Success(listOf()))
         viewModel = HistoryViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isNotNull()
    }

    @Test
    fun `check getting history state return loading`() = runTest {
        `when`(interactor.getHistoryData()).thenReturn(WordsState.Loading)
         viewModel = HistoryViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isEqualTo(WordsState.Loading)
    }

    @Test
    fun `check getting history state return success`() = runTest {
        `when`(interactor.getHistoryData()).thenReturn(WordsState.Success(listOf()))
         viewModel = HistoryViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest())
            .isEqualTo(WordsState.Success(listOf()))
    }

    @Test
    fun `check getting history state return error`() = runTest {
        `when`(interactor.getHistoryData())
            .thenReturn(WordsState.Error(Throwable("Test error")))
         viewModel = HistoryViewModel(interactor, UnconfinedTestDispatcher())
        val value: WordsState.Error =
            viewModel.currentData.getOrAwaitValueTest() as WordsState.Error
        assertThat(value.error.message).isEqualTo(Throwable("Test error").message)
    }
}