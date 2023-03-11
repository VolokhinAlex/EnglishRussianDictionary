package com.volokhinaleksey.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.database.di.databaseTestingModule
import com.volokhinaleksey.models.local.ExampleEntity
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
class ExampleDaoTest : KoinTest {

    private val database: DictionaryDatabase by inject()
    private lateinit var exampleDao: ExampleDao
    private lateinit var meaningDao: MeaningDao
    private lateinit var wordDao: WordDao

    @Before
    fun setUp() {
        startKoin {
            modules(databaseTestingModule)
        }
        exampleDao = database.exampleDao()
        meaningDao = database.meaningDao()
        wordDao = database.wordDao()
        runTest {
            wordDao.insert(WordEntity(0, ""))
            meaningDao.insert(MeaningEntity(0, translation = "Солнце", "", 0, "", "", ""))
            wordDao.insert(WordEntity(1, ""))
            meaningDao.insert(MeaningEntity(1, translation = "Солнце", "", 1, "", "", ""))
        }
    }

    @Test
    fun getExampleWordById_returnTrue() = runTest {
        val exampleEntity = ExampleEntity(exampleText = "delete this entity", meaningId = 0)
        exampleDao.insert(exampleEntity)
        val result = exampleDao.getExampleWordById(meaningId = 0)
        assertThat(result).containsExactly(exampleEntity)
    }

    @Test
    fun insertExampleWords_returnTrue() = runTest {
        val exampleEntities = listOf(
            ExampleEntity(exampleText = "delete this entity", meaningId = 0),
            ExampleEntity(exampleText = "delete this entity", meaningId = 1)
        )
        exampleDao.insert(exampleEntities)
        val result = exampleDao.getExampleWordById(exampleEntities[1].meaningId)
        assertThat(result).contains(exampleEntities[1])
    }

    @Test
    fun insertExampleWord_returnTrue() = runTest {
        val exampleEntity = ExampleEntity(exampleText = "delete this entity", meaningId = 0)
        exampleDao.insert(exampleEntity)
        val result = exampleDao.getExampleWordById(exampleEntity.meaningId)
        assertThat(result).contains(exampleEntity)
    }

    @Test
    fun deleteExampleWords_returnTrue() = runTest {
        val exampleEntity = ExampleEntity(exampleText = "delete this entity", meaningId = 0)
        exampleDao.insert(exampleEntity)
        exampleDao.delete(exampleEntity)
        val result = exampleDao.getExampleWordById(exampleEntity.meaningId)
        assertThat(result).doesNotContain(exampleEntity)
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

}