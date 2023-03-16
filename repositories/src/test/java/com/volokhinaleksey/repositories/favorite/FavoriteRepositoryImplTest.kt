package com.volokhinaleksey.repositories.favorite

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.models.ui.FavoriteWord
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
class FavoriteRepositoryImplTest {

    private lateinit var repository: FavoriteRepository

    @Mock
    private lateinit var favoriteDataSource: FavoriteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FavoriteRepositoryImpl(favoriteDataSource)
    }

    @Test
    fun `checking getFavoritesData() method calls return true`() = runTest {
        Mockito.`when`(favoriteDataSource.getFavorites()).thenReturn(emptyList())
        repository.getFavoritesData()
        verify(favoriteDataSource, times(1)).getFavorites()
    }

    @Test
    fun `checking data receipt from a data source return true`() = runTest {
        val mockData = mock(FavoriteWord::class.java)
        Mockito.`when`(favoriteDataSource.getFavorites()).thenReturn(listOf(mockData))
        val result = repository.getFavoritesData()
        assertThat(result).isEqualTo(listOf(mockData))
    }

    @Test
    fun `checking the deletion of an element from the data source Successful`() =
        runTest {
            val mockData = mock(FavoriteWord::class.java)
            Mockito.`when`(favoriteDataSource.deleteFavoriteWord(mockData)).then {}
            repository.deleteFavoriteWord(mockData)
            verify(favoriteDataSource, times(1)).deleteFavoriteWord(mockData)
        }

}