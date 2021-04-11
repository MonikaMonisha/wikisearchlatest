package com.example.daytodayfinalapplication.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()

}
