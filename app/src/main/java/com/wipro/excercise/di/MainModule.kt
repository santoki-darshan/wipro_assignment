package com.wipro.excercise.di

import androidx.lifecycle.ViewModelProviders
import com.wipro.excercise.networking.APIService
import com.wipro.excercise.ui.main.MainFragment
import com.wipro.excercise.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {

    @Provides
    fun getAPIService(): APIService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://dl.dropboxusercontent.com/")
            .client(OkHttpClient.Builder().build())
            .build().create(APIService::class.java)
    }

    @Provides
    fun mainViewModel(fragment: MainFragment): MainViewModel {
        return ViewModelProviders.of(fragment).get(MainViewModel::class.java)
    }
}