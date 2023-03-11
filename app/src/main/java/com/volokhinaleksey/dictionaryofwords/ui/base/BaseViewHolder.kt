package com.volokhinaleksey.dictionaryofwords.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T, V : ViewBinding>(binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: T)

}