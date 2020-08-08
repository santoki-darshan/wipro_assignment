package com.wipro.excercise.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wipro.excercise.data.Response
import com.wipro.excercise.networking.APIService
import com.wipro.excercise.usecase.FetchListUseCase
import com.wipro.excercise.usecase.UseCase

class MainViewModel : ViewModel() {

    val liveList: MutableLiveData<Response> = MutableLiveData()

    fun refresh(apiService: APIService, errorCallback: () -> Unit) {
        FetchListUseCase(
            apiService,
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