package com.wipro.excercise.usecase

import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T : UseCase.Input> {

    internal abstract fun executeUseCase(input: T)

    protected val disposable = CompositeDisposable()

    interface Input

    interface Callback<T> {
        fun onSuccess(response: T)

        fun onError(error: Throwable)
    }

    protected fun dispose() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }
}