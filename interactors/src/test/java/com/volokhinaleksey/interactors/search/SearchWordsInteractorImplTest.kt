package com.volokhinaleksey.interactors.search

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.mapperutils.mapWordDTOToWordUI
import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.repositories.search.SearchWordsRepository
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
class SearchWordsInteractorImplTest {

    private lateinit var interactor: SearchWordsInteractor<WordsState>

    @Mock
    private lateinit var repository: SearchWordsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = SearchWordsInteractorImpl(repository)
    }

    @Test
    fun `checking getWordsData() method calls return true`() = runTest {
        Mockito.`when`(repository.getWordsData("word", true)).thenReturn(emptyList())
        interactor.getWordsData("word", true)
        verify(repository, times(1)).getWordsData("word", true)
    }

    @Test
    fun `checking the return of a success state from the interactor`() = runTest {
        val mockWordDTO = listOf(mock(WordDTO::class.java))
        Mockito.`when`(repository.getWordsData("word", true)).thenReturn(mockWordDTO)
        val result = interactor.getWordsData("word", true)
        assertThat(result).isEqualTo(WordsState.Success(mockWordDTO.map { mapWordDTOToWordUI(it) }))
    }
}