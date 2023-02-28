package com.volokhinaleksey.dictionaryofwords.utils

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

/**
 * Mapper for convenient display of a list of word meanings in a string
 */

fun convertMeaningsToString(meanings: List<MeaningDTO>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}