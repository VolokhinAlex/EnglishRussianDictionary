package com.volokhinaleksey.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.database.di.databaseTestingModule
import com.volokhinaleksey.models.local.FavoriteEntity
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
class FavoriteDaoTest : KoinTest {

    private val database: DictionaryDatabase by inject()
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var wordDao: WordDao

    @Before
    fun setUp() {
        startKoin {
            modules(databaseTestingModule)
        }
        wordDao = database.wordDao()
        favoriteDao = database.favoriteDao()
        runTest {
            wordDao.insert(WordEntity(0, "favorite"))
            wordDao.insert(WordEntity(1, "sunset"))
        }
    }

    @Test
    fun getAllFavoriteWord_returnTrue() = runTest {
        val favoriteWord = listOf(
            FavoriteEntity(wordId = 0, isFavorite = true, word = "favorite"),
            FavoriteEntity(wordId = 1, isFavorite = true, word = "favorite")
        )
        favoriteDao.insert(favoriteWord)
        val result = favoriteDao.getFavoriteWords()
        assertThat(result).isEqualTo(favoriteWord)
    }

    @Test
    fun insertFavoriteWord_returnTrue() = runTest {
        val favoriteWord = FavoriteEntity(wordId = 0, isFavorite = true, word = "favorite")
        favoriteDao.insert(favoriteWord)
        val result = favoriteDao.getFavoriteWords()
        assertThat(result).contains(favoriteWord)
    }

    @Test
    fun updateFavoriteWord_returnTrue() = runTest {
        val favoriteWord = FavoriteEntity(wordId = 0, isFavorite = false, word = "favorite")
        val favoriteWordForUpdate =
            FavoriteEntity(wordId = 0, isFavorite = true, word = "favorite sun")
        favoriteDao.insert(favoriteWord)
        favoriteDao.update(favoriteWordForUpdate)
        val result = favoriteDao.getFavoriteWord(wordId = 0)
        assertThat(result).isEqualTo(favoriteWordForUpdate)
    }

    @Test
    fun deleteFavoriteWord_returnTrue() = runTest {
        val favoriteWord = FavoriteEntity(wordId = 0, isFavorite = true, word = "favorite")
        favoriteDao.insert(favoriteWord)
        favoriteDao.delete(favoriteWord)
        val result = favoriteDao.getFavoriteWords()
        assertThat(result).doesNotContain(favoriteWord)
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

}