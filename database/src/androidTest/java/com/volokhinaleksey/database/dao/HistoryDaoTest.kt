package com.volokhinaleksey.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.database.di.databaseTestingModule
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.local.WordEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class HistoryDaoTest : KoinTest {

    private val database: DictionaryDatabase by inject()
    private lateinit var wordDao: WordDao
    private lateinit var historyDao: HistoryDao

    @Before
    fun setUp() {
        startKoin {
            modules(databaseTestingModule)
        }
        wordDao = database.wordDao()
        historyDao = database.historyDao()
        runTest {
            wordDao.insert(WordEntity(0, "something"))
            wordDao.insert(WordEntity(1, "sunset"))
        }
    }

    @Test
    fun insertHistory_returnTrue() = runTest {
        val historyEntity = HistoryEntity(word = "something", wordId = 0)
        historyDao.insert(historyEntity)
        val result = historyDao.all()
        assertThat(result).contains(historyEntity)
    }

    @Test
    fun allHistory_returnTrue() = runTest {
        val historyEntities = listOf(
            HistoryEntity(word = "something", wordId = 0),
            HistoryEntity(word = "sunset", wordId = 1)
        )
        historyDao.insert(historyEntities)
        val result = historyDao.all()
        assertThat(result).isNotEmpty()
    }

    @Test
    fun deleteHistory_returnTrue() = runTest {
        val historyEntityForDelete = HistoryEntity(word = "something", wordId = 0)
        historyDao.insert(historyEntityForDelete)
        historyDao.delete(historyEntityForDelete)
        val result = historyDao.all()
        assertThat(result).doesNotContain(historyEntityForDelete)
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

}