package com.wipro.excercise.usecase

import com.wipro.excercise.data.Response
import io.reactivex.Observable

class FetchListUseCase(
    private val observable: Observable<Response>,
    private val callback: Callback<Response>
) :
    UseCase<FetchListUseCase.Input>() {

    override fun executeUseCase(input: Input) {
        disposable.add(
            observable.subscribe({
                callback.onSuccess(it)
                dispose()
            }, {
                callback.onError(it)
                dispose()
            })
        )
    }

    class Input : UseCase.Input
}