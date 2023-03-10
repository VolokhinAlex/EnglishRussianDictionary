package com.volokhinaleksey.history.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.volokhinaleksey.core.databinding.ItemWordBinding
import com.volokhinaleksey.core.ui.base.BaseAdapter
import com.volokhinaleksey.core.ui.base.BaseViewHolder
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.models.ui.Word

/**
 * Adapter class for a list of search history
 */

class HistoryAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val onItemClickListener: (Word) -> Unit
) : BaseAdapter<Word, ItemWordBinding, HistoryAdapter.ViewHolder>() {

    /**
     * ViewHolder for filling elements with data
     */

    inner class ViewHolder(private val binding: ItemWordBinding) :
        BaseViewHolder<Word, ItemWordBinding>(binding) {

        override fun bind(data: Word) {
            binding.word.text = data.word
            binding.descriptionWord.text = data.meanings.joinToString { it.translation.translation }
            imageLoader.loadImage(
                url = "https:${data.meanings.firstOrNull()?.imageUrl}",
                target = binding.imageView
            )
        }

    }

    /**
     * Method for creating a ViewHolder
     */

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