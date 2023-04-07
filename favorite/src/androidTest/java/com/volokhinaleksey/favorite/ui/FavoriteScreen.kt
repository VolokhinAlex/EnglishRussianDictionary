package com.volokhinaleksey.favorite.ui

import com.kaspersky.kaspresso.screens.KScreen
import com.volokhinaleksey.favorite.R
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object FavoriteScreen : KScreen<FavoriteScreen>() {

    override val layoutId: Int = R.layout.fragment_favorite_words
    override val viewClass: Class<*> = FavoriteWordsFragment::class.java

    val favoriteWords = KRecyclerView(builder = { withId(R.id.favorite_words_list) }, {})

    val reloadButton = KButton { withId(com.volokhinaleksey.core.R.id.reload_button) }
    val errorMessage = KTextView { withId(com.volokhinaleksey.core.R.id.error_message) }

}