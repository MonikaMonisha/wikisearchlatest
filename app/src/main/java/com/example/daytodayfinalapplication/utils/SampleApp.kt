package com.example.daytodayfinalapplication.utils

import com.bumptech.glide.BuildConfig
import com.example.daytodayfinalapplication.framework.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class SampleApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)

        return appComponent
    }

}
