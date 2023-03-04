package com.volokhinaleksey.core.ui.imageloader

import android.widget.ImageView
import coil.load

/**
 * Implementation of the interface for inserting images into applications.
 * This class receives images from the network using the Coil library
 */

class CoilImageLoader : ImageLoader<ImageView> {

    /**
     * The method downloads an image from the network and inserts it into a container
     *
     * @param url - Image url to get it
     * @param target - Image container to insert it
     */

    override fun loadImage(url: String, target: ImageView) {
        target.load(url)
    }

}