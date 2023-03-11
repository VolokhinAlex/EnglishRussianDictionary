package com.volokhinaleksey.mapperutils

import com.volokhinaleksey.models.local.ExampleEntity
import com.volokhinaleksey.models.local.FavoriteEntity
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.local.MeaningEntity
import com.volokhinaleksey.models.local.SimilarTranslationEntity
import com.volokhinaleksey.models.local.WordEntity
import com.volokhinaleksey.models.remote.DefinitionDTO
import com.volokhinaleksey.models.remote.ExampleDTO
import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.remote.SimilarTranslationDTO
import com.volokhinaleksey.models.remote.TranslationDTO
import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.models.ui.Definition
import com.volokhinaleksey.models.ui.ExampleWord
import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.models.ui.Meaning
import com.volokhinaleksey.models.ui.SimilarTranslation
import com.volokhinaleksey.models.ui.Translation
import com.volokhinaleksey.models.ui.Word

fun mapListWordDTOToListWordEntity(wordDTO: List<WordDTO>): List<WordEntity> = wordDTO.map {
    WordEntity(
        id = it.id ?: 0,
        word = it.text.orEmpty()
    )
}

fun mapMeaningsEntityToMeaningsList(
    meaningEntity: List<MeaningEntity>,
    exampleEntity: List<ExampleEntity>,
    similarTranslationEntity: List<SimilarTranslationEntity>
): List<MeaningDTO> =
    meaningEntity.map { meaning ->
        MeaningDTO(
            id = meaning.id,
            translation = TranslationDTO(translation = meaning.translation),
            imageUrl = meaning.imageUrl,
            wordId = meaning.wordId,
            text = meaning.word,
            transcription = meaning.transcription,
            definition = DefinitionDTO(text = meaning.definition),
            examples = exampleEntity.map { mapExampleEntityToExampleDTO(it) },
            similarTranslation = similarTranslationEntity.map {
                mapSimilarTranslationEntityToSimilarTranslationDTO(
                    it
                )
            }
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

fun mapWordDTOToHistoryEntity(wordDTO: WordDTO): HistoryEntity =
    HistoryEntity(
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

fun mapFavoriteWordToFavoriteEntity(favoriteWord: FavoriteWord): FavoriteEntity =
    FavoriteEntity(
        wordId = favoriteWord.wordId,
        isFavorite = favoriteWord.isFavorite,
        word = favoriteWord.word
    )

fun mapTranslationDTOToTranslationUI(translationDTO: TranslationDTO): Translation =
    Translation(translation = translationDTO.translation ?: "")

fun mapDefinitionDTOToDefinitionUi(definitionDTO: DefinitionDTO): Definition =
    Definition(wordDefinition = definitionDTO.text ?: "")

fun mapExampleDTOToExampleWordUi(exampleDTO: ExampleDTO): ExampleWord = ExampleWord(
    exampleWord = exampleDTO.text ?: ""
)

fun mapSimilarTranslationDTOToSimilarTranslationUI(similarTranslationDto: SimilarTranslationDTO): SimilarTranslation =
    SimilarTranslation(
        partOfSpeechAbbreviation = similarTranslationDto.partOfSpeechAbbreviation ?: "",
        translation = mapTranslationDTOToTranslationUI(
            translationDTO = similarTranslationDto.translation ?: TranslationDTO(
                translation = ""
            )
        )
    )

fun mapMeaningDtoToMeaningUI(meaningDTO: MeaningDTO): Meaning =
    Meaning(
        id = meaningDTO.id ?: 0,
        translation = mapTranslationDTOToTranslationUI(
            meaningDTO.translation ?: TranslationDTO(
                translation = ""
            )
        ),
        imageUrl = meaningDTO.imageUrl ?: "",
        wordId = meaningDTO.wordId ?: 0,
        word = meaningDTO.text ?: "",
        transcription = meaningDTO.transcription ?: "",
        definition = mapDefinitionDTOToDefinitionUi(
            meaningDTO.definition ?: DefinitionDTO(text = "")
        ),
        examples = meaningDTO.examples?.map {
            mapExampleDTOToExampleWordUi(it)
        } ?: emptyList(),
        similarTranslation = meaningDTO.similarTranslation?.map {
            mapSimilarTranslationDTOToSimilarTranslationUI(
                it
            )
        } ?: emptyList()
    )

fun mapWordDTOToWordUI(wordDTO: WordDTO): Word = Word(
    id = wordDTO.id ?: 0,
    word = wordDTO.text ?: "",
    meanings = wordDTO.meanings?.map { mapMeaningDtoToMeaningUI(it) } ?: emptyList()
)

fun mapWordUIToWordDTO(word: Word): WordDTO = WordDTO(
    id = word.id,
    text = word.word,
    meanings = word.meanings.map { mapMeaningUIToMeaningDto(it) }
)

fun mapTranslationUIToTranslationDTO(translation: Translation): TranslationDTO = TranslationDTO(
    translation = translation.translation
)

fun mapDefinitionUIToDefinitionDTO(definition: Definition): DefinitionDTO = DefinitionDTO(
    text = definition.wordDefinition
)

fun mapExampleWordToExampleDTO(exampleWord: ExampleWord): ExampleDTO = ExampleDTO(
    text = exampleWord.exampleWord
)

fun mapSimilarTranslationUIToSimilarTranslationDTO(similarTranslation: SimilarTranslation): SimilarTranslationDTO =
    SimilarTranslationDTO(
        partOfSpeechAbbreviation = similarTranslation.partOfSpeechAbbreviation,
        translation = mapTranslationUIToTranslationDTO(similarTranslation.translation)
    )

fun mapMeaningUIToMeaningDto(meaning: Meaning): MeaningDTO = MeaningDTO(
    id = meaning.id,
    translation = mapTranslationUIToTranslationDTO(meaning.translation),
    imageUrl = meaning.imageUrl,
    wordId = meaning.wordId,
    text = meaning.word,
    transcription = meaning.transcription,
    definition = mapDefinitionUIToDefinitionDTO(meaning.definition),
    examples = meaning.examples.map { mapExampleWordToExampleDTO(it) },
    similarTranslation = meaning.similarTranslation.map {
        mapSimilarTranslationUIToSimilarTranslationDTO(
            it
        )
    }
)

fun mapSimilarTranslationDTOToSimilarTranslationEntity(
    meaningId: Long,
    similarTranslationDto: SimilarTranslationDTO
): SimilarTranslationEntity =
    SimilarTranslationEntity(
        meaningId = meaningId,
        partOfSpeechAbbreviation = similarTranslationDto.partOfSpeechAbbreviation.orEmpty(),
        translation = similarTranslationDto.translation?.translation.orEmpty()
    )

fun mapSimilarTranslationEntityToSimilarTranslationDTO(
    similarTranslationEntity: SimilarTranslationEntity
): SimilarTranslationDTO = SimilarTranslationDTO(
    partOfSpeechAbbreviation = similarTranslationEntity.partOfSpeechAbbreviation,
    translation = TranslationDTO(translation = similarTranslationEntity.translation)
)

fun mapExampleEntityToExampleDTO(
    exampleEntity: ExampleEntity
): ExampleDTO = ExampleDTO(
    text = exampleEntity.exampleText
)

fun mapExampleDTOToExampleEntity(
    meaningId: Long,
    exampleDto: ExampleDTO
): ExampleEntity = ExampleEntity(
    meaningId = meaningId,
    exampleText = exampleDto.text.orEmpty()
)