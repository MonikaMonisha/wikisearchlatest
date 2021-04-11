package com.example.daytodayfinalapplication.framework.di

import com.example.daytodayfinalapplication.data.repositories.impl.SearchDataRepository
import com.example.daytodayfinalapplication.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsSearchRepository(searchRepository: SearchDataRepository): SearchRepository

}
