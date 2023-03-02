package com.volokhinaleksey.dictionaryofwords.repository.meanings

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

/**
 * Interface for a repository of values obtained from some data source
 */

interface MeaningsRepository {

    /**
     * Method for getting a list of word meanings by meaning ID from some data source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): List<MeaningDTO>

}