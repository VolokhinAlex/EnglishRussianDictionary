package com.volokhinaleksey.dictionaryofwords.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.volokhinaleksey.core.ui.base.BaseAdapter
import com.volokhinaleksey.core.ui.base.BaseViewHolder
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.dictionaryofwords.databinding.ItemWordBinding
import com.volokhinaleksey.models.remote.WordDTO


class HistoryAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val onItemClickListener: (WordDTO) -> Unit
) : BaseAdapter<WordDTO, ItemWordBinding, HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemWordBinding) :
        BaseViewHolder<WordDTO, ItemWordBinding>(binding) {

        override fun bind(data: WordDTO) {
            binding.word.text = data.text
            binding.descriptionWord.text = data.meanings?.joinToString { it.translation?.translation.orEmpty() }
            val imageUrl = data.meanings?.firstOrNull()?.imageUrl
            if (!imageUrl.isNullOrEmpty()) {
                imageLoader.loadImage(
                    url = imageUrl.substring(imageUrl.indexOf("https")),
                    target = binding.imageView
                )
            }
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