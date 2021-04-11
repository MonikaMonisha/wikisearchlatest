package com.example.daytodayfinalapplication.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daytodayfinalapplication.domain.usecase.SearchUseCase
import com.example.daytodayfinalapplication.utils.SearchState
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val searchState = MutableLiveData<SearchState>()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .switchMap { query ->
                Timber.i("Got query: $query")
                searchUseCase.execute(SearchUseCase.Companion.Params(query))
                    .toObservable()
                    .onErrorResumeNext { e: Throwable ->
                        handleSearchError(e)
                        Timber.e(e, "Failed to search wikipedia: 1")
                        Observable.just(emptyList())
                    }
            }
            .subscribe({ results ->
                Timber.i("Got results: ${results.size}")
                searchState.value = SearchState.Success(results)
            }, { e ->
                Timber.e(e, "Failed to search wikipedia: 2")
                handleSearchError(e)
            })
            .addTo(searchUseCase.disposables)
    }

    private fun handleSearchError(e: Throwable) {
        when (e) {
            is IOException -> {
                searchState.value = SearchState.Error("You're not connected to Internet!")
            }
            else -> {
                searchState.value = SearchState.Error("Someone messed up!")
            }
        }
    }

    fun search(query: String) {
        publishSubject.onNext(query)
    }

    fun searchResult(): LiveData<SearchState> = searchState

    override fun onCleared() {
        searchUseCase.dispose()
    }

}
