package com.volokhinaleksey.interactors.description

import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.repositories.meanings.MeaningsRepository

/**
 * Implementation of an interface that describes the logic of the application that does not depend on the
 *  framework and platform in any way
 */

class WordDescriptionInteractorImpl(
    private val repository: MeaningsRepository
) : WordDescriptionInteractor {

    /**
     * A method for getting the meanings of a word from some source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): MeaningsState {
        return MeaningsState.Success(repository.getMeaningsData(
            meaningId = meaningId,
            isRemoteSource = isRemoteSource
        ).map {
            mapMeaningDtoToMeaningUI(it)
        })
    }

    /**
     * Method for saving a favorite word
     * @param word - The word to save
     */

    override suspend fun saveFavoriteWord(word: FavoriteWord) {
        repository.saveFavoriteWord(word = word)
    }

    /**
     * Method for getting a favorite word by its id
     * @param wordId - Word ID
     */

    override suspend fun getFavoriteWord(wordId: Long): FavoriteState {
        val favoriteData = repository.getFavoriteWord(wordId = wordId)
        return if (favoriteData == null) {
            FavoriteState.Success(emptyList())
        } else {
            FavoriteState.Success(listOf(favoriteData))
        }
    }

}