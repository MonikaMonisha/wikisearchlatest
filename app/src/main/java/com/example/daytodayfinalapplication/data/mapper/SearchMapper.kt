package com.example.daytodayfinalapplication.data.mapper

import com.example.daytodayfinalapplication.domain.entity.SearchEntity
import com.example.daytodayfinalapplication.domain.entity.SearchResult
import javax.inject.Inject

open class SearchMapper @Inject constructor() : DataMapper<SearchEntity, SearchResult> {

    override fun mapToEntity(type: SearchResult): SearchEntity {
        return SearchEntity(
            type.pageId,
            type.query,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

    override fun mapFromEntity(type: SearchEntity): SearchResult {
        return SearchResult(
            type.pageId,
            type.query,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

}
