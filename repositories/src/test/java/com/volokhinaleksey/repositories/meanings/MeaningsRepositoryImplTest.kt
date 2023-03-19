package com.volokhinaleksey.repositories.meanings

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.datasource.description.DescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.models.remote.MeaningDTO
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
class MeaningsRepositoryImplTest {

    private lateinit var repository: MeaningsRepository

    @Mock
    private lateinit var remoteDataSource: DescriptionDataSource

    @Mock
    private lateinit var localDataSource: LocalDescriptionDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MeaningsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `checking getMeaningsData() method calls return true`() = runTest {
        val meaningId = 0L
        Mockito.`when`(remoteDataSource.getMeaningsData(meaningId)).thenReturn(emptyList())
        repository.getMeaningsData(meaningId, true)
        verify(remoteDataSource, times(1)).getMeaningsData(meaningId)
    }

    @Test
    fun `checking getMeaningsData() method getting data from a remote data source return true`() =
        runTest {
            val meaningId = 0L
            val mockData = listOf(mock(MeaningDTO::class.java))
            Mockito.`when`(remoteDataSource.getMeaningsData(meaningId)).thenReturn(mockData)
            val result = repository.getMeaningsData(meaningId, true)
            assertThat(result).isEqualTo(mockData)
        }

    @Test
    fun `checking getMeaningsData() method getting data from a local data source return true`() =
        runTest {
            val meaningId = 0L
            val mockData = listOf(mock(MeaningDTO::class.java))
            Mockito.`when`(localDataSource.getMeaningsData(meaningId)).thenReturn(mockData)
            val result = repository.getMeaningsData(meaningId, false)
            assertThat(result).isEqualTo(mockData)
        }

    @Test
    fun `checking data retention in the method return Successful`() = runTest {
        val mockData = mock(FavoriteWord::class.java)
        Mockito.`when`(localDataSource.saveFavoriteWord(mockData)).then {}
        repository.saveFavoriteWord(mockData)
        verify(localDataSource, times(1)).saveFavoriteWord(mockData)
    }

    @Test
    fun `checking getFavoriteWord() method calls return true`() = runTest {
        val mockData = mock(FavoriteWord::class.java)
        Mockito.`when`(localDataSource.getFavoriteWord(0L)).thenReturn(mockData)
        repository.getFavoriteWord(0L)
        verify(localDataSource, times(1)).getFavoriteWord(0L)
    }

    @Test
    fun `checking getMeaningsData() method getting data from a data source return true`() =
        runTest {
            val mockData = mock(FavoriteWord::class.java)
            Mockito.`when`(localDataSource.getFavoriteWord(0L)).thenReturn(mockData)
            val result = repository.getFavoriteWord(0L)
            assertThat(result).isEqualTo(mockData)
        }
}