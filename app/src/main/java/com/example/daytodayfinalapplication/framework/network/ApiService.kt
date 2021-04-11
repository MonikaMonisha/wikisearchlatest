package com.example.daytodayfinalapplication.framework.network

import com.example.daytodayfinalapplication.domain.entity.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api.php")
    fun search(
        @Query("gpssearch") query: String,
        @Query("format") format: String = "json",
        @Query("action") action: String = "query",
        @Query("generator") generator: String = "prefixsearch",
        @Query("gpsnamespace") gpsnamespace: String = "0",
        @Query("gpslimit") gpslimit: String = "10",
        @Query("prop") prop: String = "pageimages|pageterms",
        @Query("piprop") piprop: String = "thumbnail",
        @Query("wbptterms") wbptterms: String = "description",
        @Query("pithumbsize") pithumbsize: String = "50",
        @Query("pilimit") pilimit: String = "10",
        @Query("formatversion") formatversion: String = "2"
    ): Single<SearchResponse>

}
