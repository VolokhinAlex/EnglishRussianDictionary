package com.volokhinaleksey.datasource.search

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteSearchDataSourceTest {

    private lateinit var remoteSearchDataSource: SearchDataSource

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteSearchDataSource = RemoteSearchDataSource(DictionaryApiHolder(apiService))
    }

    @Test
    fun `checking the getWordsData() method call`() = runTest {
        remoteSearchDataSource.getWordsData("")
        verify(apiService, times(1)).getWordsBySearch(any())
    }

    @Test
    fun `checking the received data for not null`() = runTest {
        Mockito.`when`(apiService.getWordsBySearch(any())).thenReturn(listOf())
        assertThat(remoteSearchDataSource.getWordsData("")).isNotNull()
    }

    @Test
    fun `checking the received data for not empty`() = runTest {
        Mockito.`when`(apiService.getWordsBySearch(any())).thenReturn(listOf(mock()))
        assertThat(remoteSearchDataSource.getWordsData("")).isNotEmpty()
    }

}