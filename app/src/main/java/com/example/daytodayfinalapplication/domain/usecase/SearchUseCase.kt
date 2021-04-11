package com.example.daytodayfinalapplication.domain.usecase

import com.example.daytodayfinalapplication.domain.entity.SearchResult
import com.example.daytodayfinalapplication.domain.repository.SearchRepository
import com.example.daytodayfinalapplication.utils.PostExecutionThread
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val searchRepository: SearchRepository
) : SingleUseCase<List<SearchResult>, SearchUseCase.Companion.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params): Single<List<SearchResult>> {
        return searchRepository.search(params.query)
    }

    companion object {
        data class Params(val query: String)
    }

}
