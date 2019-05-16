package com.margento.sampleapp.data.remote

import com.margento.sampleapp.data.remote.request.SampleRequest
import com.margento.sampleapp.data.remote.response.SampleResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {

    @GET("samplePath")
    abstract fun getSampleData(@Body sampleRequest :  SampleRequest) : Deferred<SampleResponse>

}