package com.volokhinaleksey.datasource.favorite

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalFavoriteDataSourceTest {

    private lateinit var dataSource: FavoriteDataSource

    @Before
    fun setUp() {
        dataSource = FakeLocalFavoriteDataSource()
    }

    @Test
    fun `checking the received data from methods getHistoryData() for not null`() = runTest {
        assertThat(dataSource.getFavorites()).isNotNull()
    }

    @Test
    fun `checking the received data from methods getHistoryData() for not empty`() = runTest {
        assertThat(dataSource.getFavorites()).isNotEmpty()
    }
}