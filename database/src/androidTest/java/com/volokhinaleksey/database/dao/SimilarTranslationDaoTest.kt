package com.volokhinaleksey.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.database.di.databaseTestingModule
import com.volokhinaleksey.models.local.MeaningEntity
import com.volokhinaleksey.models.local.SimilarTranslationEntity
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
class SimilarTranslationDaoTest : KoinTest {

    private val database: DictionaryDatabase by inject()
    private lateinit var similarTranslationDao: SimilarTranslationDao
    private lateinit var meaningDao: MeaningDao
    private lateinit var wordDao: WordDao

    @Before
    fun setUp() {
        startKoin {
            modules(databaseTestingModule)
        }
        similarTranslationDao = database.similarTranslationDao()
        meaningDao = database.meaningDao()
        wordDao = database.wordDao()
        runTest {
            wordDao.insert(WordEntity(0, ""))
            meaningDao.insert(MeaningEntity(0, translation = "Солнце", "", 0, "", "", ""))
        }
    }

    @Test
    fun insertSimilarTranslationListEntity_returnTrue() = runTest {
        val similarTranslationEntities = listOf(
            SimilarTranslationEntity(
                translation = "Солнце",
                meaningId = 0,
                partOfSpeechAbbreviation = "сущ."
            ), SimilarTranslationEntity(
                translation = "Солнышко",
                meaningId = 0,
                partOfSpeechAbbreviation = "сущ."
            )
        )
        similarTranslationDao.insert(similarTranslationEntities)
        val result = similarTranslationDao.getSimilarTranslationById(0)
        assertThat(result).isEqualTo(similarTranslationEntities)
    }

    @Test
    fun insertSimilarTranslationEntity_returnTrue() = runTest {
        val similarTranslationEntity = SimilarTranslationEntity(
            translation = "Солнце",
            meaningId = 0,
            partOfSpeechAbbreviation = "сущ."
        )
        similarTranslationDao.insert(similarTranslationEntity)
        val similarTranslationResult =
            similarTranslationDao.getSimilarTranslationById(similarTranslationEntity.meaningId)
        assertThat(similarTranslationResult).contains(similarTranslationEntity)
    }

    @Test
    fun updateSimilarTranslationEntity_returnTrue() = runTest {
        val similarTranslationEntity = SimilarTranslationEntity(
            translation = "Солнце",
            meaningId = 0,
            partOfSpeechAbbreviation = "сущ."
        )
        similarTranslationDao.insert(similarTranslationEntity)
        val newSimilarTranslationEntity = SimilarTranslationEntity(
            translation = "Дождь",
            meaningId = 0,
            partOfSpeechAbbreviation = "сущ."
        )
        similarTranslationDao.insert(newSimilarTranslationEntity)
        val similarTranslationResult =
            similarTranslationDao.getSimilarTranslationById(similarTranslationEntity.meaningId)
        assertThat(similarTranslationResult).contains(newSimilarTranslationEntity)
    }

    @Test
    fun deleteSimilarTranslationEntity_returnTrue() = runTest {
        val similarTranslationEntity = SimilarTranslationEntity(
            translation = "Солнце",
            meaningId = 0,
            partOfSpeechAbbreviation = "сущ."
        )
        similarTranslationDao.insert(similarTranslationEntity)
        similarTranslationDao.delete(similarTranslationEntity)
        val similarTranslationResult =
            similarTranslationDao.getSimilarTranslationById(similarTranslationEntity.meaningId)
        assertThat(similarTranslationResult).doesNotContain(similarTranslationEntity)
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

}