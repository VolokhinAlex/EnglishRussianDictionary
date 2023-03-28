package com.volokhinaleksey.favorite.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.favorite.getOrAwaitValueTest
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.models.states.FavoriteState
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
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @Mock
    private lateinit var interactor: FavoriteInteractor

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test calling getFavorites() method`() = runTest {
        Mockito.`when`(interactor.getFavorites()).thenReturn(FavoriteState.Success(listOf()))
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        verify(interactor, atLeastOnce()).getFavorites()
    }

    @Test
    fun `test return data is not null return true`() = runTest {
        Mockito.`when`(interactor.getFavorites()).thenReturn(FavoriteState.Success(listOf()))
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isNotNull()
    }

    @Test
    fun `check getting word state return loading`() = runTest {
        Mockito.`when`(interactor.getFavorites()).thenReturn(FavoriteState.Loading)
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isEqualTo(FavoriteState.Loading)
    }

    @Test
    fun `check getting word state return success`() = runTest {
        Mockito.`when`(interactor.getFavorites()).thenReturn(FavoriteState.Success(listOf()))
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        assertThat(viewModel.currentData.getOrAwaitValueTest()).isEqualTo(
            FavoriteState.Success(
                listOf()
            )
        )
    }

    @Test
    fun `check getting word state return error`() = runTest {
        Mockito.`when`(interactor.getFavorites())
            .thenReturn(FavoriteState.Error(Throwable("Test error")))
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        val value: FavoriteState.Error =
            viewModel.currentData.getOrAwaitValueTest() as FavoriteState.Error
        assertThat(value.error.message).isEqualTo(Throwable("Test error").message)
    }

    @Test
    fun `check delete favorite word return true`() = runTest {
        Mockito.`when`(interactor.deleteFavoriteWord(any())).thenReturn(any())
        viewModel = FavoriteViewModel(interactor, UnconfinedTestDispatcher())
        viewModel.deleteFavoriteWord(FavoriteState.Success(emptyList()))
        verify(interactor, times(1)).deleteFavoriteWord(any())
    }

}