package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

class FakeLocalFavoriteDataSource : FavoriteDataSource {

    private val favoriteWords = mutableListOf<FavoriteWord>()

    override suspend fun getFavorites(): List<FavoriteWord> {
        return favoriteWords
    }

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        if (favoriteWords.isNotEmpty()) {
            favoriteWords.remove(word)
        }
    }

}