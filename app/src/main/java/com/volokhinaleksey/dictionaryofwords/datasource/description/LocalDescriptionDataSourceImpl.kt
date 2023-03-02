package com.volokhinaleksey.dictionaryofwords.datasource.description

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsListToMeaningsEntity

class LocalDescriptionDataSourceImpl(
    private val database: DictionaryDatabase
) : LocalDescriptionDataSource {

    override suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>) {
        database.meaningDao().insert(mapMeaningsListToMeaningsEntity(meaningDTO))
    }

    /**
     * Method for getting a list of word meanings from a local data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return mapMeaningsEntityToMeaningsList(
            database.meaningDao().getWordMeaningById(meaningId = meaningId)
        )
    }
}