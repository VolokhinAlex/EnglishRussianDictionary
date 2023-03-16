package com.volokhinaleksey.repositories.search

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.datasource.search.LocalSearchDataSource
import com.volokhinaleksey.datasource.search.SearchDataSource
import com.volokhinaleksey.models.remote.WordDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SearchWordsRepositoryImplTest {

    private lateinit var repository: SearchWordsRepository

    @Mock
    private lateinit var remoteDataSource: SearchDataSource

    @Mock
    private lateinit var localDataSource: LocalSearchDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SearchWordsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `checking whether the method of getting data from a remote data source is called return True`() =
        runTest {
            val wordForGet = "sun"
            val wordData = mock(WordDTO::class.java)
            Mockito.`when`(remoteDataSource.getWordsData(wordForGet)).thenReturn(listOf(wordData))
            repository.getWordsData(wordForGet, true)
            verify(remoteDataSource, times(1)).getWordsData(wordForGet)
        }

    @Test
    fun `checking whether the method of getting data from a local data source is called return True`() =
        runTest {
            val wordForGet = "sun"
            val wordData = mock(WordDTO::class.java)
            Mockito.`when`(localDataSource.getWordsData(wordForGet)).thenReturn(listOf(wordData))
            repository.getWordsData(wordForGet, false)
            verify(localDataSource, times(1)).getWordsData(wordForGet)
        }

    @Test
    fun `checking get data from remote data source return True`() = runTest {
        val wordForGet = "sun"
        val wordData = mock(WordDTO::class.java)
        Mockito.`when`(remoteDataSource.getWordsData(wordForGet)).thenReturn(listOf(wordData))
        assertThat(repository.getWordsData(wordForGet, true)).isEqualTo(listOf(wordData))
    }

    @Test
    fun `checking get data from local data source return True`() = runTest {
        val wordForGet = "sun"
        val wordData = mock(WordDTO::class.java)
        Mockito.`when`(localDataSource.getWordsData(wordForGet)).thenReturn(listOf(wordData))
        assertThat(repository.getWordsData(wordForGet, false)).isEqualTo(listOf(wordData))
    }

    @Test
    fun `checking for an empty response from the data source Return True`() = runTest {
        val wordData = mock(WordDTO::class.java)
        Mockito.`when`(localDataSource.getWordsData(anyString())).thenReturn(listOf(wordData))
        assertThat(repository.getWordsData("sun", false)).doesNotContain(listOf(wordData))
    }
}