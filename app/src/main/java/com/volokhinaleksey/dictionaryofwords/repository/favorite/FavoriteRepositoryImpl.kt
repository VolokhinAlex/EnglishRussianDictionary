package com.volokhinaleksey.dictionaryofwords.repository.favorite

import com.volokhinaleksey.dictionaryofwords.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord

class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {
    override suspend fun getFavoritesData(): List<FavoriteWord> {
        return favoriteDataSource.getFavorites()
    }

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        favoriteDataSource.deleteFavoriteWord(word = word)
    }

}