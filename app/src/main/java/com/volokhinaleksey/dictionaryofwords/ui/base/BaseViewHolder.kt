package com.volokhinaleksey.dictionaryofwords.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * The ViewHolder base class for creating your ViewHolders based on it.
 * This class is required for the Adapter Base Class.
 * The ViewHolder base class contains one bind method
 * The constructor of the base ViewHolder takes in some object inherited from ViewBinding
 */

abstract class BaseViewHolder<T, V : ViewBinding>(binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * A method for filling in any list items with data
     * @param data - Data class for filling in the element
     */

    abstract fun bind(data: T)

}