package com.volokhinaleksey.datasource.history

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalHistoryDataSourceTest {

    private lateinit var dataSource: HistoryDataSource

    @Before
    fun setUp() {
        dataSource = FakeLocalHistoryDataSource()
    }

    @Test
    fun `checking the received data from methods getFavorites() for not null`() = runTest {
        assertThat(dataSource.getHistoryData()).isNotNull()
    }

    @Test
    fun `checking the received data from methods getFavorites() for not empty`() = runTest {
        assertThat(dataSource.getHistoryData()).isNotEmpty()
    }
}