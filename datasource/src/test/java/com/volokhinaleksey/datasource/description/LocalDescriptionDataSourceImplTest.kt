package com.volokhinaleksey.datasource.description

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDescriptionDataSourceImplTest {

    private lateinit var dataSource: LocalDescriptionDataSource

    @Before
    fun setUp() {
        dataSource = FakeLocalDescriptionDataSource()
    }

    @Test
    fun `checking the received data from methods getMeaningsData() for not null`() = runTest {
        assertThat(dataSource.getMeaningsData(0)).isNotNull()
    }

    @Test
    fun `checking the received data from methods getMeaningsData() for not empty`() = runTest {
        assertThat(dataSource.getMeaningsData(0)).isNotEmpty()
    }

    @Test
    fun `checking the received data from methods getFavoriteWord() for not null`() = runTest {
        assertThat(dataSource.getMeaningsData(1)).isNotNull()
    }

    @Test
    fun `checking the received data from methods getFavoriteWord() for not empty`() = runTest {
        assertThat(dataSource.getFavoriteWord(1)).isEqualTo(FavoriteWord(1, "", true, listOf()))
    }

    @Test
    fun `checking whether a word is saved to a data source`() = runTest {
        val mockMeanings = mock<MeaningDTO>()
        dataSource.saveWordToDB(listOf(mockMeanings))
        assertThat(dataSource.getMeaningsData(0)).contains(mockMeanings)
    }

    @Test
    fun `checking whether a favorite word is saved to a data source`() = runTest {
        val mockFavoriteWord = FavoriteWord(4, "", true, listOf())
        dataSource.saveFavoriteWord(mockFavoriteWord)
        assertThat(dataSource.getFavoriteWord(4)).isEqualTo(mockFavoriteWord)
    }
}