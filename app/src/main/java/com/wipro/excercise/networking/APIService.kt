package com.wipro.excercise.networking

import com.wipro.excercise.data.Response
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun fetchList(): Single<Response>
}