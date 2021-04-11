package com.example.daytodayfinalapplication.data.source

import com.example.daytodayfinalapplication.domain.entity.SearchEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the cache service.
 *
 * This is to be implemented by the database layer, using this interface as a way of communicating.
 */
interface SearchCache {

    fun search(query: String): Single<List<SearchEntity>>

    fun isQueryCached(query: String): Single<Boolean>

    fun cacheSearch(query: String, result: List<SearchEntity>): Completable

}
