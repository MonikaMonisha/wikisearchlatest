package com.example.daytodayfinalapplication.data.repositories.impl

import com.example.daytodayfinalapplication.data.mapper.SearchMapper
import com.example.daytodayfinalapplication.data.source.SearchCache
import com.example.daytodayfinalapplication.data.source.SearchRemote
import com.example.daytodayfinalapplication.domain.entity.SearchResult
import com.example.daytodayfinalapplication.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of [SearchRepository].
 */
class SearchDataRepository @Inject constructor(
    private val mapper: SearchMapper,
    private val remote: SearchRemote,
    private val cache: SearchCache
) : SearchRepository {

    override fun search(query: String): Single<List<SearchResult>> {
        return cache.isQueryCached(query)
            .flatMap { isCached ->
                if (isCached) {
                    cache.search(query)
                } else {
                    remote.search(query)
                        .doAfterSuccess { results ->
                            cache.cacheSearch(query, results)
                                .subscribe({
                                    // query cached
                                }, {
                                    // cache failed
                                })
                        }
                }
            }
            .map { entities ->
                entities.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

}
