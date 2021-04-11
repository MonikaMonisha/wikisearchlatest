package com.example.daytodayfinalapplication.domain.usecase

import com.example.daytodayfinalapplication.utils.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    val disposables = CompositeDisposable()

    protected abstract fun buildUseCase(params: Params): Flowable<T>

    open fun execute(params: Params): Flowable<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}
