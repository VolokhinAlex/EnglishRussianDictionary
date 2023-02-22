package com.volokhinaleksey.dictionaryofwords.ui.imageloaders

interface ImageLoader<T> {
    fun loadImage(url: String, target: T)
}