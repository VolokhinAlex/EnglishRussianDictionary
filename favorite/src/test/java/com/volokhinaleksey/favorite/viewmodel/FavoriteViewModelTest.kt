package com.volokhinaleksey.favorite.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.favorite.getOrAwaitValueTest
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.models.states.FavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Mock
    lateinit var favoriteInteractor: FavoriteInteractor

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = FavoriteViewModel(favoriteInteractor)
    }

    @Test
    fun `when getFavorites() is called, interactor should return success state`() = runTest {
        val state = FavoriteState.Success(emptyList())
        Mockito.`when`(favoriteInteractor.getFavorites()).thenReturn(state)
        viewModel.getFavorites()
        dispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.currentData.getOrAwaitValueTest()
        assertThat(result).isEqualTo(state)
    }

    @Test
    fun `when getFavorites() is called, the interactor should return an error status`() = runTest {
        Mockito.`when`(favoriteInteractor.getFavorites())
            .thenReturn(FavoriteState.Error(RuntimeException("Something went wrong")))
        viewModel.getFavorites()
        dispatcher.scheduler.advanceUntilIdle()
        val result = when (viewModel.currentData.getOrAwaitValueTest()) {
            is FavoriteState.Error -> true
            else -> false
        }
        assertThat(result).isTrue()
    }

    @Test
    fun `when getFavorites() is called, interactor should return loading state`() = runTest {
        Mockito.`when`(favoriteInteractor.getFavorites())
            .thenReturn(FavoriteState.Loading)
        viewModel.getFavorites()
        dispatcher.scheduler.advanceUntilIdle()
        val result = when (viewModel.currentData.getOrAwaitValueTest()) {
            is FavoriteState.Loading -> true
            else -> false
        }
        assertThat(result).isTrue()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun deleteFavoriteWord_ReturnTrue() = runTest {
//        val favoriteWord = FavoriteState.Success(
//            listOf(
//                FavoriteWord(
//                    wordId = 0,
//                    word = "book",
//                    isFavorite = false,
//                    meanings = listOf()
//                )
//            )
//        )
//        `when`(favoriteInteractor.getFavorites()).thenReturn(favoriteWord)
//        viewModel.deleteFavoriteWord(favoriteWord)
//        viewModel.getFavorites()
//        assertThat(viewModel.data.getOrAwaitValueTest()).isNotEqualTo(favoriteWord)
//    }

}