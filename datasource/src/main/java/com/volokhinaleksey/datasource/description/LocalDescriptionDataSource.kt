package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.models.ui.FavoriteWord

interface LocalDescriptionDataSource : DescriptionDataSource {

    suspend fun saveWordToDB(meaningDTO: List<com.volokhinaleksey.models.remote.MeaningDTO>)

    suspend fun saveFavoriteWord(favoriteWord: FavoriteWord)

    suspend fun getFavoriteWordFlag(wordId: Long): FavoriteWord?
}