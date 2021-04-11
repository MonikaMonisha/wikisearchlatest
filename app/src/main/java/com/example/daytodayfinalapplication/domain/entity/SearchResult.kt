package com.example.daytodayfinalapplication.domain.entity

/**
 * SearchResult domain entity.
 */
data class SearchResult(
    val pageId: Int,
    val query: String,
    val index: Int,
    val title: String,
    val description: String?,
    val imageUrl: String?
)
