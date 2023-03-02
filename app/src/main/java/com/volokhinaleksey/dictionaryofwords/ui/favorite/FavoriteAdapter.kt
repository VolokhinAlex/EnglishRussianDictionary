package com.volokhinaleksey.dictionaryofwords.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.volokhinaleksey.dictionaryofwords.databinding.ItemWordBinding
import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseAdapter
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseViewHolder
import com.volokhinaleksey.dictionaryofwords.utils.convertMeaningsToString

class FavoriteAdapter(
    private val onItemClickListener: (FavoriteWord) -> Unit
) : BaseAdapter<FavoriteWord, ItemWordBinding, FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemWordBinding) :
        BaseViewHolder<FavoriteWord, ItemWordBinding>(binding) {

        override fun bind(data: FavoriteWord) {
            binding.word.text = data.word
            binding.descriptionWord.text = convertMeaningsToString(data.meanings)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onItemClickListener(currentList[layoutPosition])
            }
        }
    }

}
