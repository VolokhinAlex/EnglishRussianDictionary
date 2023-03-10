package com.volokhinaleksey.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.models.local.WordEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class WordDaoTest {

    private lateinit var dictionaryDatabase: DictionaryDatabase
    private lateinit var wordDao: WordDao

    @Before
    fun createDb() {
        dictionaryDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DictionaryDatabase::class.java
        ).allowMainThreadQueries().build()
        wordDao = dictionaryDatabase.wordDao()
    }

    @Test
    fun getWordByWord_returnTrue() = runTest {
        val wordEntityToInsert1 = WordEntity(id = 0, word = "testing")
        val wordEntityToInsert2 = WordEntity(id = 1, word = "search")
        wordDao.insert(wordEntityToInsert1)
        wordDao.insert(wordEntityToInsert2)
        val words = wordDao.getDataByWord("search")
        assertThat(words).containsExactly(wordEntityToInsert2)
    }

    @Test
    fun insertWord_returnTrue() = runTest {
        val wordEntityToInsert = WordEntity(id = 0, word = "database_test")
        wordDao.insert(wordEntityToInsert)
        val words = wordDao.getDataByWord(wordEntityToInsert.word)
        assertThat(words).contains(wordEntityToInsert)
    }

    @Test
    fun deleteWord_returnTrue() = runTest {
        val wordEntityToInsert = WordEntity(id = 0, word = "database_test")
        wordDao.insert(wordEntityToInsert)
        wordDao.delete(wordEntityToInsert)
        val words = wordDao.getDataByWord(wordEntityToInsert.word)
        assertThat(words).doesNotContain(wordEntityToInsert)
    }

    @Test
    fun updateWord_returnTrue() = runTest {
        val oldWordEntity = WordEntity(id = 0, word = "database_test")
        wordDao.insert(oldWordEntity)
        val newWordEntity = WordEntity(id = 0, word = "updated word")
        wordDao.update(newWordEntity)
        val words = wordDao.getDataByWord(newWordEntity.word)
        assertThat(words).contains(newWordEntity)
    }

    @After
    fun tearDown() {
        dictionaryDatabase.close()
    }

}