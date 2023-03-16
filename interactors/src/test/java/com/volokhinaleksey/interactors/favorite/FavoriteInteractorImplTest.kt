package com.volokhinaleksey.interactors.favorite

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.repositories.favorite.FavoriteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteInteractorImplTest {

    private lateinit var favoriteInteractor: FavoriteInteractor

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        favoriteInteractor = FavoriteInteractorImpl(favoriteRepository)
    }

    @Test
    fun `checking getFavoritesData() method calls return true`() = runTest {
        Mockito.`when`(favoriteRepository.getFavoritesData()).thenReturn(emptyList())
        favoriteInteractor.getFavorites()
        verify(favoriteRepository, times(1)).getFavoritesData()
    }

    @Test
    fun `checking the return of a data from the interactor`() = runTest {
        val mockData = listOf(mock(FavoriteWord::class.java))
        Mockito.`when`(favoriteRepository.getFavoritesData()).thenReturn(mockData)
        val result = favoriteInteractor.getFavorites()
        assertThat(result).isEqualTo(FavoriteState.Success(mockData))
    }

    @Test
    fun `checking the deletion of an element return Successful`() =
        runTest {
            val mockData = mock(FavoriteWord::class.java)
            Mockito.`when`(favoriteRepository.deleteFavoriteWord(mockData)).then {}
            favoriteInteractor.deleteFavoriteWord(FavoriteState.Success(listOf(mockData)))
            verify(favoriteRepository, times(1)).deleteFavoriteWord(mockData)
        }
}