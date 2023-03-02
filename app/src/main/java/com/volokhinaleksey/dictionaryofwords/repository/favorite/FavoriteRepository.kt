package com.volokhinaleksey.dictionaryofwords.repository.favorite

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord

interface FavoriteRepository {

    suspend fun getFavoritesData(): List<FavoriteWord>

}