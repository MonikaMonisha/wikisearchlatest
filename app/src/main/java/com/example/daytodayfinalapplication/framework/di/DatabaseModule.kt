package com.example.daytodayfinalapplication.framework.di

import android.app.Application
import com.example.daytodayfinalapplication.data.source.SearchCache
import com.example.daytodayfinalapplication.database.SearchCacheImpl
import com.example.daytodayfinalapplication.database.db.SearchDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): SearchDatabase {
            return SearchDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindSearchCache(searchDatabase: SearchCacheImpl): SearchCache

}
