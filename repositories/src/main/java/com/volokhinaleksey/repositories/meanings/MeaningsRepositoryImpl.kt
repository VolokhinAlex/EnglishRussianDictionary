package com.volokhinaleksey.repositories.meanings

import com.volokhinaleksey.datasource.description.DescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.models.remote.MeaningDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class MeaningsRepositoryImpl(
    private val remoteDataSource: DescriptionDataSource,
    private val localDataSource: LocalDescriptionDataSource
) : MeaningsRepository {

    /**
     * Method for getting a list of word meanings by meaning ID from some data source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getMeaningsData(
        meaningId: Long,
        isRemoteSource: Boolean
    ): List<MeaningDTO> {
        return if (isRemoteSource) {
            val meaningsData = remoteDataSource.getMeaningsData(meaningId = meaningId)
            localDataSource.saveWordToDB(meaningDTO = meaningsData)
            meaningsData
        } else {
            localDataSource.getMeaningsData(meaningId = meaningId)
        }
    }

    override suspend fun saveFavoriteWord(word: com.volokhinaleksey.models.remote.FavoriteWord) {
        localDataSource.saveFavoriteWord(favoriteWord = word)
    }

    override suspend fun getFavoriteWordFlag(wordId: Long): com.volokhinaleksey.models.remote.FavoriteWord? {
        return localDataSource.getFavoriteWordFlag(wordId = wordId)
    }

}