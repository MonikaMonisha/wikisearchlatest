package com.example.daytodayfinalapplication.framework.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        RemoteModule::class,
        ActivityModule::class,
        DataModule::class,
        PresentationModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
