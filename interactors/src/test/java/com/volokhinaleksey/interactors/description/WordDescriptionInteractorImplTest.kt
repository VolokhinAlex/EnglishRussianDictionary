package com.volokhinaleksey.interactors.description

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.repositories.meanings.MeaningsRepository
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
class WordDescriptionInteractorImplTest {

    private lateinit var interactor: WordDescriptionInteractor

    @Mock
    private lateinit var repository: MeaningsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = WordDescriptionInteractorImpl(repository)
    }

    @Test
    fun `checking getMeaningsData() method calls return true`() = runTest {
        Mockito.`when`(repository.getMeaningsData(0, true)).thenReturn(emptyList())
        interactor.getMeaningsData(0, true)
        verify(repository, times(1)).getMeaningsData(0, true)
    }

    @Test
    fun `checking the return of a success state from the getMeaningsData() method`() = runTest {
        val mockData = listOf(mock(MeaningDTO::class.java))
        Mockito.`when`(repository.getMeaningsData(0, true)).thenReturn(mockData)
        val result = interactor.getMeaningsData(0, true)
        assertThat(result).isEqualTo(MeaningsState.Success(mockData.map {
            mapMeaningDtoToMeaningUI(
                it
            )
        }))
    }

    @Test
    fun `checking getFavoriteWord() method calls return true`() = runTest {
        Mockito.`when`(repository.getFavoriteWord(wordId = 0L))
            .thenReturn(mock(FavoriteWord::class.java))
        interactor.getFavoriteWord(wordId = 0L)
        verify(repository, times(1)).getFavoriteWord(0L)
    }

    @Test
    fun `checking the return of a success state from the getFavoriteWord() method`() = runTest {
        val mockData = mock(FavoriteWord::class.java)
        Mockito.`when`(repository.getFavoriteWord(wordId = 0L)).thenReturn(mockData)
        val result = interactor.getFavoriteWord(0L)
        assertThat(result).isEqualTo(FavoriteState.Success(listOf(mockData)))
    }

    @Test
    fun `checking saveFavoriteWord() method calls return true`() = runTest {
        val mockData = mock(FavoriteWord::class.java)
        repository.saveFavoriteWord(mockData)
        verify(repository, times(1)).saveFavoriteWord(mockData)
    }
}