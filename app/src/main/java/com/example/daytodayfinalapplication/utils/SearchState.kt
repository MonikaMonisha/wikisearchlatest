package com.example.daytodayfinalapplication.utils

import com.example.daytodayfinalapplication.domain.entity.SearchResult


sealed class SearchState {

    class Error(val message: String) : SearchState()

    class Success(val result: List<SearchResult>) : SearchState()

}
