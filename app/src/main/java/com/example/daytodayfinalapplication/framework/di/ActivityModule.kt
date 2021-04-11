package com.example.daytodayfinalapplication.framework.di

import com.example.daytodayfinalapplication.utils.PostExecutionThread
import com.example.daytodayfinalapplication.utils.UiThread
import com.example.daytodayfinalapplication.view.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}
