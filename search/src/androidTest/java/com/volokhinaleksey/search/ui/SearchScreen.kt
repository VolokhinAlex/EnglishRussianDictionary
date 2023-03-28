package com.volokhinaleksey.search.ui

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.search.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.recycler.KRecyclerView

object SearchScreen : KScreen<SearchScreen>() {

    override val layoutId: Int = R.layout.fragment_dictionary_of_words
    override val viewClass: Class<*> = DictionaryOfWordsFragment::class.java

    val searchInput = KTextInputLayout { withId(R.id.search_input_layout) }

    val wordsList = KRecyclerView(builder = { withId(R.id.words_list) }, {})

    val offlineMessageView = KView { withId(R.id.offline_message) }
}