package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

class FakeLocalFavoriteDataSource : FavoriteDataSource {

    private val favoriteWords = mutableListOf(
        FavoriteWord(0, "", false, listOf()),
        FavoriteWord(0, "", true, listOf()),
        FavoriteWord(0, "", false, listOf()),
        FavoriteWord(0, "", true, listOf())
    )

    override suspend fun getFavorites(): List<FavoriteWord> {
        return favoriteWords
    }

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        favoriteWords.remove(word)
    }

}