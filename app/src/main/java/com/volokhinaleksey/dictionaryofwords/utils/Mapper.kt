package com.volokhinaleksey.dictionaryofwords.utils

import com.volokhinaleksey.dictionaryofwords.model.local.FavoriteEntity
import com.volokhinaleksey.dictionaryofwords.model.local.HistoryEntity
import com.volokhinaleksey.dictionaryofwords.model.local.MeaningEntity
import com.volokhinaleksey.dictionaryofwords.model.local.WordEntity
import com.volokhinaleksey.dictionaryofwords.model.remote.DefinitionDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.TranslationDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

fun mapListWordDTOToListWordEntity(wordDTO: List<WordDTO>): List<WordEntity> = wordDTO.map {
    WordEntity(
        id = it.id ?: 0,
        word = it.text.orEmpty()
    )
}

fun mapMeaningsEntityToMeaningsList(meaningEntity: List<MeaningEntity>): List<MeaningDTO> =
    meaningEntity.map { meaning ->
        MeaningDTO(
            id = meaning.id,
            translation = TranslationDTO(translation = meaning.translation),
            imageUrl = meaning.imageUrl,
            wordId = meaning.wordId,
            text = meaning.word,
            transcription = meaning.transcription,
            definition = DefinitionDTO(text = meaning.definition),
            examples = null,
            similarTranslation = null
        )
    }

fun mapMeaningsListToMeaningsEntity(meaningDTO: List<MeaningDTO>): List<MeaningEntity> =
    meaningDTO.map { meaning ->
        MeaningEntity(
            id = meaning.id ?: 0,
            translation = meaning.translation?.translation.orEmpty(),
            imageUrl = meaning.imageUrl.orEmpty(),
            wordId = meaning.wordId ?: 0,
            transcription = meaning.transcription.orEmpty(),
            word = meaning.text.orEmpty(),
            definition = meaning.definition?.text.orEmpty()
        )
    }


fun mapWordDTOToHistoryEntity(wordDTO: WordDTO): HistoryEntity = HistoryEntity(
    word = wordDTO.text.orEmpty(),
    wordId = wordDTO.id ?: 0
)

fun mapListWordDTOToMeaningEntityList(
    meaningDTO: List<MeaningDTO>,
    wordId: Long
): List<MeaningEntity> =
    meaningDTO.map {
        MeaningEntity(
            id = it.id ?: 0,
            translation = it.translation?.translation.orEmpty(),
            imageUrl = it.imageUrl.orEmpty(),
            wordId = wordId,
            transcription = it.transcription.orEmpty(),
            word = it.text.orEmpty(),
            definition = it.definition?.text.toString()
        )
    }

fun mapFavoriteWordToFavoriteEntity(favoriteWord: FavoriteWord): FavoriteEntity = FavoriteEntity(
    wordId = favoriteWord.wordId,
    isFavorite = favoriteWord.isFavorite,
    word = favoriteWord.word
)