package com.volokhinaleksey.dictionaryofwords.di.component

import android.app.Application
import com.volokhinaleksey.dictionaryofwords.app.DictionaryApp
import com.volokhinaleksey.dictionaryofwords.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        SchedulersProviderModule::class,
        ImageModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(dictionaryApp: DictionaryApp)
}