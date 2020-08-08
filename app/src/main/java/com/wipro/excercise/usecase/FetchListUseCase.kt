package com.wipro.excercise.usecase

import com.wipro.excercise.data.Response
import com.wipro.excercise.networking.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchListUseCase(
    private val apiService: APIService,
    private val callback: Callback<Response>
) :
    UseCase<FetchListUseCase.Input>() {

    override fun executeUseCase(input: Input) {
        disposable.add(
            apiService.fetchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
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