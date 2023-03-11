package com.volokhinaleksey.repositories.meanings

import com.volokhinaleksey.datasource.description.DescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

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

    /**
     * Method for saving a favorite word
     * @param word - The word to save
     */


    override suspend fun saveFavoriteWord(word: FavoriteWord) {
        localDataSource.saveFavoriteWord(favoriteWord = word)
    }

    /**
     *  A method for getting a favorite word by word id. The method returns either the selected word or null.
     *  @param wordId - Id of the word to search for favorites
     */

    override suspend fun getFavoriteWord(wordId: Long): FavoriteWord? {
        return localDataSource.getFavoriteWord(wordId = wordId)
    }

}