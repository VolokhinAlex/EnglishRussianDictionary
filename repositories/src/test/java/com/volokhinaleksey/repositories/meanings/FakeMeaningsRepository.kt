package com.volokhinaleksey.repositories.meanings

import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

class FakeMeaningsRepository : MeaningsRepository {

    private val meanings: MutableList<MeaningDTO> = mutableListOf()

    private val favoriteWords: MutableList<FavoriteWord> = mutableListOf()

    override suspend fun getMeaningsData(
        meaningId: Long,
        isRemoteSource: Boolean
    ): List<MeaningDTO> {
        return meanings.filter { it.id == meaningId }
    }

    override suspend fun saveFavoriteWord(word: FavoriteWord) {
        favoriteWords.add(word)
    }

    override suspend fun getFavoriteWord(wordId: Long): FavoriteWord? {
        favoriteWords.forEach {
            if (it.wordId == wordId) {
                return it
            }
        }
        return null
    }
}