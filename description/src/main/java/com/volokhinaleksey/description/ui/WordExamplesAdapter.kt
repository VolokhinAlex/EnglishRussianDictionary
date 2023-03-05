package com.volokhinaleksey.description.ui

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.volokhinaleksey.core.ui.base.BaseAdapter
import com.volokhinaleksey.core.ui.base.BaseViewHolder
import com.volokhinaleksey.description.databinding.ItemExampleBinding
import com.volokhinaleksey.models.remote.ExampleDTO

/**
 * Adapter class for a list of examples of the use of a word in the text
 */

class WordExamplesAdapter :
    BaseAdapter<ExampleDTO, ItemExampleBinding, WordExamplesAdapter.ViewHolder>() {

    /**
     * ViewHolder for filling elements with data
     */

    inner class ViewHolder(private val binding: ItemExampleBinding) :
        BaseViewHolder<ExampleDTO, ItemExampleBinding>(binding) {

        override fun bind(data: ExampleDTO) {
            val startIndex = data.text?.indexOf("[") ?: 0
            val endIndex = data.text?.indexOf("]") ?: 0
            val spannable = SpannableString(data.text)
            if (startIndex >= 0 && endIndex >= 0) {
                spannable.setSpan(
                    ForegroundColorSpan(Color.MAGENTA),
                    startIndex,
                    endIndex + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            binding.textExample.text = spannable
        }

    }

    /**
     * Method for creating a ViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}