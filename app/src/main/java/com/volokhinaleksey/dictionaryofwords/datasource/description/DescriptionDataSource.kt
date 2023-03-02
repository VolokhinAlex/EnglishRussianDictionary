package com.volokhinaleksey.dictionaryofwords.datasource.description

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface DescriptionDataSource {

    /**
     * Method for getting a list of word meanings.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO>

}