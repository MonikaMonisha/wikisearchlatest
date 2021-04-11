package com.example.daytodayfinalapplication.framework.network

import com.example.daytodayfinalapplication.data.mapper.SearchEntityMapper
import com.example.daytodayfinalapplication.data.source.SearchRemote
import com.example.daytodayfinalapplication.domain.entity.SearchEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of [SearchRemote].
 */
class SearchRemoteImpl @Inject constructor(
    private val mapper: SearchEntityMapper,
    private val service: ApiService
) : SearchRemote {

    override fun search(query: String): Single<List<SearchEntity>> {
        return service.search(query)
            .map { response ->
                response.query.pages
                    .sortedBy { it.index }
                    .map { page -> mapper.mapToEntity(query to page) }
            }
    }

}
