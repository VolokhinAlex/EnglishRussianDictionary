package com.volokhinaleksey.interactors.history

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.mapperutils.mapWordDTOToWordUI
import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.repositories.history.HistoryRepository
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
class HistoryInteractorImplTest {

    private lateinit var interactor: HistoryInteractor<WordsState>

    @Mock
    private lateinit var repository: HistoryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = HistoryInteractorImpl(repository)
    }

    @Test
    fun `checking getHistoryData() method calls return true`() = runTest {
        Mockito.`when`(repository.getHistoryData()).thenReturn(emptyList())
        interactor.getHistoryData()
        verify(repository, times(1)).getHistoryData()
    }

    @Test
    fun `checking the return of a success state from the interactor`() = runTest {
        val mockWordDTO = listOf(mock(WordDTO::class.java))
        Mockito.`when`(repository.getHistoryData()).thenReturn(mockWordDTO)
        val result = interactor.getHistoryData()
        assertThat(result).isEqualTo(WordsState.Success(mockWordDTO.map { mapWordDTOToWordUI(it) }))
    }
}