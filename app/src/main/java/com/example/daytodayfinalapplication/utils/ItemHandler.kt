package com.example.daytodayfinalapplication.utils

import com.example.daytodayfinalapplication.domain.entity.SearchResult

interface ItemHandler {
    fun invoke(searchResult: SearchResult)
}
