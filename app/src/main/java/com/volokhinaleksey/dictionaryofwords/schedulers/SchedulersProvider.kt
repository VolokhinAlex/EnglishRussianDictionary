package com.volokhinaleksey.dictionaryofwords.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface SchedulersProvider {

    fun mainThread(): Scheduler

    fun io(): Scheduler

}

class SchedulersProviderImpl : SchedulersProvider {

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

}