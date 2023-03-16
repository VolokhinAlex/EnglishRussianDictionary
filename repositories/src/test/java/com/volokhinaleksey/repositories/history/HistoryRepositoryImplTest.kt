package com.volokhinaleksey.repositories.history

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.datasource.history.HistoryDataSource
import com.volokhinaleksey.models.remote.WordDTO
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
class HistoryRepositoryImplTest {

    private lateinit var repository: HistoryRepository

    @Mock
    private lateinit var historyDataSource: HistoryDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = HistoryRepositoryImpl(historyDataSource)
    }

    @Test
    fun `checking the call of the data acquisition method return true`() = runTest {
        Mockito.`when`(historyDataSource.getHistoryData()).thenReturn(emptyList())
        repository.getHistoryData()
        verify(historyDataSource, times(1)).getHistoryData()
    }

    @Test
    fun `checking data receipt from a data source return true`() = runTest {
        val mockData = listOf(mock(WordDTO::class.java))
        Mockito.`when`(historyDataSource.getHistoryData()).thenReturn(mockData)
        val result = repository.getHistoryData()
        assertThat(result).isEqualTo(mockData)
    }


}