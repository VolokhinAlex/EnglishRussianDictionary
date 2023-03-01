package com.volokhinaleksey.dictionaryofwords.ui.imageloaders

import android.widget.ImageView
import coil.load

class CoilImageLoader : ImageLoader<ImageView> {

    override fun loadImage(url: String, target: ImageView) {
        target.load(url)
    }

}