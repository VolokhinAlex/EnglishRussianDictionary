package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.datasource.ApiHolder

/**
 * Implementation of the interface for receiving data from a remote data source.
 */

class RemoteDescriptionDataSource(
    private val apiHolder: ApiHolder
) : DescriptionDataSource {

    /**
     * Method for getting a list of word meanings from a remote data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<com.volokhinaleksey.models.remote.MeaningDTO> {
        return apiHolder.apiService.getMeanings(meaningId = meaningId)
    }


}