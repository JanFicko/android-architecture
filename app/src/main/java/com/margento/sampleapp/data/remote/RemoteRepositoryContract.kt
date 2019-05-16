package com.margento.sampleapp.data.remote

import com.margento.sampleapp.data.remote.request.SampleRequest
import com.margento.sampleapp.data.remote.response.SampleResponse
import kotlinx.coroutines.Deferred

interface RemoteRepositoryContract {

    fun getSampleData(sampleRequest: SampleRequest) : Deferred<SampleResponse>

}