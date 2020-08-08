package com.wipro.excercise.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wipro.excercise.data.Response
import com.wipro.excercise.usecase.FetchListUseCase
import com.wipro.excercise.usecase.UseCase
import io.reactivex.Observable

class MainViewModel : ViewModel() {

    val liveList: MutableLiveData<Response> = MutableLiveData()

    fun refresh(observable: Observable<Response>, errorCallback: () -> Unit) {
        FetchListUseCase(
            observable,
            object : UseCase.Callback<Response> {
                override fun onSuccess(response: Response) {
                    liveList.value = response
                }

                override fun onError(error: Throwable) {
                    errorCallback.invoke()
                }
            }).executeUseCase(FetchListUseCase.Input())
    }
}