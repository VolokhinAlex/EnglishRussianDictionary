package com.volokhinaleksey.dictionaryofwords.ui.description

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.volokhinaleksey.dictionaryofwords.databinding.ItemExampleBinding
import com.volokhinaleksey.dictionaryofwords.model.remote.ExampleDTO
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseAdapter
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseViewHolder

class WordExamplesAdapter :
    BaseAdapter<ExampleDTO, ItemExampleBinding, WordExamplesAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}