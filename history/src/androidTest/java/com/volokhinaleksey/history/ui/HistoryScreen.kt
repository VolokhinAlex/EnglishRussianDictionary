package com.volokhinaleksey.history.ui

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.history.R
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object HistoryScreen : KScreen<HistoryScreen>() {

    override val layoutId: Int = R.layout.fragment_history_search
    override val viewClass: Class<*> = HistorySearchFragment::class.java

    val historyWords = KRecyclerView(builder = { withId(R.id.history_words_list) }, {})

    val reloadButton = KButton { withId(com.volokhinaleksey.core.R.id.reload_button) }
    val errorMessage = KTextView { withId(com.volokhinaleksey.core.R.id.error_message) }
}