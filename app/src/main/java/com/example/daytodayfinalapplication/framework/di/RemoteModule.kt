package com.example.daytodayfinalapplication.framework.di

import android.app.Application
import com.bumptech.glide.BuildConfig
import com.example.daytodayfinalapplication.data.source.SearchRemote
import com.example.daytodayfinalapplication.framework.network.ApiService
import com.example.daytodayfinalapplication.framework.network.ApiServiceFactory
import com.example.daytodayfinalapplication.framework.network.SearchRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.io.File

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun file(application: Application): File {
            val file = File(application.cacheDir, "OkHttpCache")
            file.mkdirs()
            return file
        }

        @Provides
        @JvmStatic
        fun provideApiService(cacheDir: File): ApiService {
            return ApiServiceFactory.makeApiService(BuildConfig.DEBUG, cacheDir)
        }

    }

    @Binds
    abstract fun bindsSearchRemote(searchRemote: SearchRemoteImpl): SearchRemote

}
