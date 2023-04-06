package com.volokhinaleksey.datasource.description

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
class RemoteDescriptionDataSourceTest {

    private lateinit var remoteDescriptionDataSource: DescriptionDataSource

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDescriptionDataSource = RemoteDescriptionDataSource(DictionaryApiHolder(apiService))
    }

    @Test
    fun `checking the getMeaningsData() method call`() = runTest {
        remoteDescriptionDataSource.getMeaningsData(2)
        verify(apiService, times(1)).getMeanings(any())
    }

    @Test
    fun `checking the received data for not null`() = runTest {
        Mockito.`when`(apiService.getMeanings(any())).thenReturn(listOf())
        assertThat(remoteDescriptionDataSource.getMeaningsData(2)).isNotNull()
    }

    @Test
    fun `checking the received data for not empty`() = runTest {
        Mockito.`when`(apiService.getMeanings(any())).thenReturn(listOf(mock()))
        assertThat(remoteDescriptionDataSource.getMeaningsData(2)).isNotEmpty()
    }
}