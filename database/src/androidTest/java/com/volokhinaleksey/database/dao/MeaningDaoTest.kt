package com.volokhinaleksey.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.database.di.databaseTestingModule
import com.volokhinaleksey.models.local.MeaningEntity
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
class MeaningDaoTest : KoinTest {

    private val database: DictionaryDatabase by inject()
    private lateinit var wordDao: WordDao
    private lateinit var meaningDao: MeaningDao

    @Before
    fun setUp() {
        startKoin {
            modules(databaseTestingModule)
        }
        wordDao = database.wordDao()
        meaningDao = database.meaningDao()
        runTest {
            wordDao.insert(WordEntity(0, ""))
            wordDao.insert(WordEntity(1, ""))
        }
    }

    @Test
    fun getWordMeaningById_returnTrue() = runTest {
        val meanings = listOf(
            MeaningEntity(
                id = 0, translation = "", imageUrl = "http", word = "Hooray",
                wordId = 0, transcription = "", definition = ""
            ), MeaningEntity(
                id = 1, translation = "", imageUrl = "https", word = "WoW",
                wordId = 1, transcription = "", definition = ""
            )
        )
        meaningDao.insert(meanings)
        val result = meaningDao.getWordMeaningById(1)
        assertThat(result).isEqualTo(listOf(meanings[1]))
    }

    @Test
    fun getMeaningByWordId_returnTrue() = runTest {
        val meaningEntity1 = MeaningEntity(
            id = 0, translation = "", imageUrl = "http", word = "Hooray",
            wordId = 0, transcription = "", definition = ""
        )
        val meaningEntity2 = MeaningEntity(
            id = 1, translation = "", imageUrl = "https", word = "WoW",
            wordId = 1, transcription = "", definition = ""
        )
        meaningDao.insert(meaningEntity1)
        meaningDao.insert(meaningEntity2)
        val result = meaningDao.getWordMeaningByWordId(0)
        assertThat(result).contains(meaningEntity1)
    }

    @Test
    fun insertListMeanings_returnTrue() = runTest {
        val meanings = listOf(
            MeaningEntity(
                id = 0, translation = "", imageUrl = "http", word = "Hooray",
                wordId = 0, transcription = "", definition = ""
            ), MeaningEntity(
                id = 1, translation = "", imageUrl = "https", word = "WoW",
                wordId = 0, transcription = "", definition = ""
            )
        )
        meaningDao.insert(meanings)
        val result = meaningDao.getWordMeaningByWordId(0)
        assertThat(result).isEqualTo(meanings)
    }

    @Test
    fun insertMeaning_returnTrue() = runTest {
        val meaningEntityForInsert = MeaningEntity(
            id = 0, translation = "", imageUrl = "", word = "",
            wordId = 0, transcription = "", definition = ""
        )
        meaningDao.insert(meaningEntityForInsert)
        val result = meaningDao.getWordMeaningById(meaningEntityForInsert.id)
        assertThat(result).containsExactly(meaningEntityForInsert)
    }

    @Test
    fun updateMeaning_returnTrue() = runTest {
        val meaningEntityForUpdate = MeaningEntity(
            id = 0, translation = "", imageUrl = "", word = "",
            wordId = 0, transcription = "", definition = ""
        )
        meaningDao.insert(meaningEntityForUpdate)
        val meaningEntityForUpdateNewModel = MeaningEntity(
            id = 0, translation = "MeaningNew", imageUrl = "https://image", word = "meaning new",
            wordId = 0, transcription = "MeaningNew", definition = "something"
        )
        meaningDao.update(meaningEntityForUpdateNewModel)
        val result = meaningDao.getWordMeaningById(meaningEntityForUpdateNewModel.id)
        assertThat(result).containsExactly(meaningEntityForUpdateNewModel)
    }

    @Test
    fun deleteMeaning_returnTrue() = runTest {
        val meaningEntityForDelete = MeaningEntity(
            id = 0, translation = "", imageUrl = "", word = "",
            wordId = 0, transcription = "", definition = ""
        )
        meaningDao.insert(meaningEntityForDelete)
        meaningDao.delete(meaningEntityForDelete)
        val result = meaningDao.getWordMeaningById(meaningEntityForDelete.id)
        assertThat(result).doesNotContain(meaningEntityForDelete)
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

}