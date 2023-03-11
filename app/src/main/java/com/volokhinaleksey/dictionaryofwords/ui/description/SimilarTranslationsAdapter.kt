package com.volokhinaleksey.dictionaryofwords.ui.description

import android.view.LayoutInflater
import android.view.ViewGroup
import com.volokhinaleksey.dictionaryofwords.databinding.ItemSimilarTranslationBinding
import com.volokhinaleksey.dictionaryofwords.model.remote.SimilarTranslationDTO
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseAdapter
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseViewHolder

/**
 * Adapter class for a list of similar translations of a word
 */

class SimilarTranslationsAdapter :
    BaseAdapter<SimilarTranslationDTO, ItemSimilarTranslationBinding, SimilarTranslationsAdapter.ViewHolder>() {

    /**
     * ViewHolder for filling elements with data
     */

    inner class ViewHolder(private val binding: ItemSimilarTranslationBinding) :
        BaseViewHolder<SimilarTranslationDTO, ItemSimilarTranslationBinding>(binding) {

        override fun bind(data: SimilarTranslationDTO) {
            binding.textExample.text = data.translation?.translation
            binding.partOfSpeechAbbreviation.text = data.partOfSpeechAbbreviation
        }

    }

    /**
     * Method for creating a ViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemSimilarTranslationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

}