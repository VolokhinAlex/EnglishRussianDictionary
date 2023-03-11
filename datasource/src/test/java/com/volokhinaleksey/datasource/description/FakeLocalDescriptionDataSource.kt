package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

class FakeLocalDescriptionDataSource : LocalDescriptionDataSource {

    private val meanings = mutableListOf<MeaningDTO>()
    private val favorites = mutableListOf<FavoriteWord>()

    override suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>) {
        meanings.addAll(meaningDTO)
    }

    override suspend fun saveFavoriteWord(favoriteWord: FavoriteWord) {
        favorites.add(favoriteWord)
    }

    override suspend fun getFavoriteWord(wordId: Long): FavoriteWord? {
        val favoriteWord = favorites.filter { it.wordId == wordId }
        return favoriteWord.ifEmpty { null }?.first()
    }

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return meanings.filter { it.id == meaningId }
    }
}