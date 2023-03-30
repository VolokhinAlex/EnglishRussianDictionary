package com.volokhinaleksey.description.ui

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.description.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object WordDescription : KScreen<WordDescription>() {

    override val layoutId: Int = R.layout.fragment_word_description
    override val viewClass: Class<*> = WordDescriptionFragment::class.java

    val backArrowIcon = KImageView { withId(R.id.back_arrow) }
    val word = KTextView { withId(R.id.word) }
    val textSpeechIcon = KImageView { withId(R.id.text_speech) }
    val favoriteCheckBox = KCheckBox { withId(R.id.add_favorite) }

    val transcription = KTextView { withId(R.id.transcription) }
    val translation = KTextView { withId(R.id.translation) }
    val partOfSpeechAbbreviation = KTextView { withId(R.id.partOfSpeechAbbreviation) }
    val similarTranslationLabel = KTextView { withId(R.id.similar_translation_label) }
    val similarTranslation = KRecyclerView({ withId(R.id.similar_translation) }, {})
    val examplesLabel = KTextView { withId(R.id.examples_label) }
    val examplesList = KRecyclerView({ withId(R.id.examples_list) }, {})
    val reloadButton = KButton { withId(com.volokhinaleksey.core.R.id.reload_button) }
    val errorMessage = KTextView { withId(com.volokhinaleksey.core.R.id.error_message) }
}