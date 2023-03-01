package com.volokhinaleksey.dictionaryofwords.di.module

import android.widget.ImageView
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.CoilImageLoader
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun imageLoader(): ImageLoader<ImageView> = CoilImageLoader()

}