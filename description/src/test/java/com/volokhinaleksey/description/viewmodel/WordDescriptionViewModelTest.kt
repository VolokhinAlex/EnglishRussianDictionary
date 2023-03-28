package com.volokhinaleksey.description.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.volokhinaleksey.description.getOrAwaitValueTest
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class WordDescriptionViewModelTest {

    private lateinit var viewModel: WordDescriptionViewModel

    @Mock
    private lateinit var interactor: WordDescriptionInteractor

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = WordDescriptionViewModel(interactor, UnconfinedTestDispatcher())
    }

    @Test
    fun `test calling getMeanings() method`() = runTest {
        Mockito.`when`(interactor.getMeaningsData(any(), any()))
            .thenReturn(MeaningsState.Success(listOf()))
        viewModel.getMeanings(any(), any())
        verify(interactor, atLeastOnce()).getMeaningsData(any(), any())
    }

    @Test
    fun `test return data is not null return true`() = runTest {
        Mockito.`when`(interactor.getMeaningsData(any(), any()))
            .thenReturn(MeaningsState.Success(listOf()))
        viewModel.getMeanings(any(), any())
        Truth.assertThat(viewModel.currentData.getOrAwaitValueTest()).isNotNull()
    }

    @Test
    fun `check getting word state return loading`() = runTest {
        Mockito.`when`(interactor.getMeaningsData(any(), any())).thenReturn(MeaningsState.Loading)
        viewModel.getMeanings(any(), any())
        Truth.assertThat(viewModel.currentData.getOrAwaitValueTest())
            .isEqualTo(MeaningsState.Loading)
    }

    @Test
    fun `check getting word state return success`() = runTest {
        Mockito.`when`(interactor.getMeaningsData(any(), any()))
            .thenReturn(MeaningsState.Success(listOf()))
        viewModel.getMeanings(any(), any())
        Truth.assertThat(viewModel.currentData.getOrAwaitValueTest())
            .isEqualTo(MeaningsState.Success(listOf()))
    }

    @Test
    fun `check getting word state return error`() = runTest {
        Mockito.`when`(interactor.getMeaningsData(any(), any()))
            .thenReturn(MeaningsState.Error(Throwable("Test error")))
        viewModel.getMeanings(any(), any())
        val value: MeaningsState.Error =
            viewModel.currentData.getOrAwaitValueTest() as MeaningsState.Error
        Truth.assertThat(value.error.message).isEqualTo(Throwable("Test error").message)
    }

    @Test
    fun `check calling method save favorite word return true`() = runTest {
        viewModel.saveFavoriteWord(mock())
        verify(interactor, atLeastOnce()).saveFavoriteWord(any())
    }

    @Test
    fun `test calling getFavoriteWord() method`() = runTest {
        Mockito.`when`(interactor.getFavoriteWord(any()))
            .thenReturn(FavoriteState.Success(listOf()))
        viewModel.getFavoriteWord(any())
        verify(interactor, atLeastOnce()).getFavoriteWord(any())
    }

    @Test
    fun `test getFavoriteWord return data is not null return true`() = runTest {
        Mockito.`when`(interactor.getFavoriteWord(any()))
            .thenReturn(FavoriteState.Success(listOf()))
        viewModel.getFavoriteWord(any())
        Truth.assertThat(viewModel.favoriteData.getOrAwaitValueTest()).isNotNull()
    }

    @Test
    fun `check getFavoriteWord value state return loading`() = runTest {
        Mockito.`when`(interactor.getFavoriteWord(any())).thenReturn(FavoriteState.Loading)
        viewModel.getFavoriteWord(any())
        Truth.assertThat(viewModel.favoriteData.getOrAwaitValueTest())
            .isEqualTo(FavoriteState.Loading)
    }

    @Test
    fun `check getFavoriteWord value state return success`() = runTest {
        Mockito.`when`(interactor.getFavoriteWord(any()))
            .thenReturn(FavoriteState.Success(listOf()))
        viewModel.getFavoriteWord(any())
        Truth.assertThat(viewModel.favoriteData.getOrAwaitValueTest())
            .isEqualTo(FavoriteState.Success(listOf()))
    }

    @Test
    fun `check getFavoriteWord value state return error`() = runTest {
        Mockito.`when`(interactor.getFavoriteWord(any()))
            .thenReturn(FavoriteState.Error(Throwable("Test error")))
        viewModel.getFavoriteWord(any())
        val value: FavoriteState.Error =
            viewModel.favoriteData.getOrAwaitValueTest() as FavoriteState.Error
        Truth.assertThat(value.error.message).isEqualTo(Throwable("Test error").message)
    }

}