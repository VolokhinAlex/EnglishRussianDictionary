package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.dictionaryofwords.databinding.ItemWordBinding
import com.volokhinaleksey.models.remote.WordDTO

/**
 * Adapter class for creating a list of words obtained.
 */

class DictionaryOfWordsAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val onItemClickListener: (WordDTO) -> Unit
) : ListAdapter<WordDTO, DictionaryOfWordsAdapter.ViewHolder>(DictionaryOfWordsCallback) {

    /**
     * ViewHolder for filling elements with data
     */

    inner class ViewHolder(private val binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wordData: WordDTO) {
            binding.word.text = wordData.text
            binding.descriptionWord.text = wordData.meanings?.joinToString { it.translation?.translation.orEmpty() }
            val imageUrl = wordData.meanings?.firstOrNull()?.imageUrl
            if (!imageUrl.isNullOrEmpty()) {
                imageLoader.loadImage(
                    url = imageUrl.substring(imageUrl.indexOf("https")),
                    target = binding.imageView
                )
            }
        }

    }

    /**
     * Method for creating a ViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
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

    /**
     * This method should determine the contents of the Recycler View.ViewHolder View
     * to display the element in the specified position.
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

/**
 * Object for defining Diffutils Callback for comparison conditions list.
 */

object DictionaryOfWordsCallback : DiffUtil.ItemCallback<WordDTO>() {

    override fun areItemsTheSame(oldItem: WordDTO, newItem: WordDTO): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WordDTO, newItem: WordDTO): Boolean {
        return oldItem.id == newItem.id
    }

}