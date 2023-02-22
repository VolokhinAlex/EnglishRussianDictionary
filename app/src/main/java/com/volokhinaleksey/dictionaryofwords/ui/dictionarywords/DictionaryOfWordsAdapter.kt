package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.dictionaryofwords.databinding.ItemWordBinding
import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader

class DictionaryOfWordsAdapter(private val imageLoader: ImageLoader<ImageView>) :
    ListAdapter<WordData, DictionaryOfWordsAdapter.ViewHolder>(DictionaryOfWordsCallback) {

    inner class ViewHolder(private val binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wordData: WordData) {
            binding.word.text = wordData.text
            binding.descriptionWord.text =
                wordData.meanings?.firstOrNull()?.translation?.translation
            val imageUrl = wordData.meanings?.firstOrNull()?.imageUrl
            if (imageUrl != null && imageUrl.isNotEmpty()) {
                imageLoader.loadImage(
                    url = imageUrl.substring(imageUrl.indexOf("https")),
                    target = binding.imageView
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

object DictionaryOfWordsCallback : DiffUtil.ItemCallback<WordData>() {

    override fun areItemsTheSame(oldItem: WordData, newItem: WordData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WordData, newItem: WordData): Boolean {
        return oldItem == newItem
    }

}