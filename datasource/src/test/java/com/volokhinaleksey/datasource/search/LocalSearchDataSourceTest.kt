package com.volokhinaleksey.datasource.search

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class LocalSearchDataSourceTest {

    private lateinit var localSearchDataSource: LocalSearchDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        localSearchDataSource = FakeLocalSearchDataSource()
    }

    @Test
    fun `checking the received data for not null`() = runTest {
        assertThat(localSearchDataSource.getWordsData("")).isNotNull()
    }

    @Test
    fun `checking the received data for not empty`() = runTest {
        assertThat(localSearchDataSource.getWordsData("")).isNotEmpty()
    }

}