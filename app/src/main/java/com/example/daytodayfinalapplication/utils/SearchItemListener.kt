package com.example.daytodayfinalapplication.utils

import com.example.daytodayfinalapplication.domain.entity.SearchResult

interface SearchItemListener {
    fun onSearchResultClicked(searchResult: SearchResult)
}
