package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapExampleDTOToExampleEntity
import com.volokhinaleksey.mapperutils.mapFavoriteWordToFavoriteEntity
import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.mapperutils.mapMeaningsListToMeaningsEntity
import com.volokhinaleksey.mapperutils.mapSimilarTranslationDTOToSimilarTranslationEntity
import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

class LocalDescriptionDataSourceImpl(
    private val database: DictionaryDatabase
) : LocalDescriptionDataSource {

    override suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>) {
        database.meaningDao().insert(mapMeaningsListToMeaningsEntity(meaningDTO))
        val similarTranslation = meaningDTO.flatMap {
            it.similarTranslation?.map { translation ->
                mapSimilarTranslationDTOToSimilarTranslationEntity(
                    meaningId = it.id ?: 0,
                    similarTranslationDto = translation
                )
            } ?: emptyList()
        }
        val examples = meaningDTO.flatMap {
            it.examples?.map { example ->
                mapExampleDTOToExampleEntity(
                    meaningId = it.id ?: 0,
                    exampleDto = example
                )
            } ?: emptyList()
        }
        database.similarTranslationDao().insert(similarTranslation)
        database.exampleDao().insert(examples)
    }

    override suspend fun saveFavoriteWord(favoriteWord: FavoriteWord) {
        database.favoriteDao().insert(mapFavoriteWordToFavoriteEntity(favoriteWord))
    }

    override suspend fun getFavoriteWordFlag(wordId: Long): FavoriteWord? {
        val favoriteWord = database.favoriteDao().getFavoriteWord(wordId = wordId)
        val meanings =
            database.meaningDao().getWordMeaningByWordId(wordId = favoriteWord?.wordId ?: 0)
        return favoriteWord?.let {
            FavoriteWord(
                wordId = favoriteWord.wordId,
                word = favoriteWord.word,
                isFavorite = favoriteWord.isFavorite,
                meanings = mapMeaningsEntityToMeaningsList(
                    meaningEntity = meanings,
                    exampleEntity = meanings.flatMap {
                        database.exampleDao().getExampleWordById(meaningId = it.id)
                    },
                    similarTranslationEntity = meanings.flatMap {
                        database.similarTranslationDao()
                            .getSimilarTranslationById(meaningId = it.id)
                    }
                ).map {
                    mapMeaningDtoToMeaningUI(it)
                }
            )
        }
    }

    /**
     * Method for getting a list of word meanings from a local data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        val meanings = database.meaningDao().getWordMeaningById(meaningId = meaningId)
        return mapMeaningsEntityToMeaningsList(
            meanings,
            exampleEntity = meanings.flatMap {
                database.exampleDao().getExampleWordById(meaningId = it.id)
            },
            similarTranslationEntity = meanings.flatMap {
                database.similarTranslationDao()
                    .getSimilarTranslationById(meaningId = it.id)
            }
        )
    }
}