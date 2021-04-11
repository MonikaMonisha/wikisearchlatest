package com.example.daytodayfinalapplication.data.source

import com.example.daytodayfinalapplication.domain.entity.SearchEntity
import io.reactivex.Single

/**
 * Interface defining methods for the remote service.
 *
 * This is to be implemented by the remote layer, using this interface as a way of communicating.
 */
interface SearchRemote {

    fun search(query: String): Single<List<SearchEntity>>

}
