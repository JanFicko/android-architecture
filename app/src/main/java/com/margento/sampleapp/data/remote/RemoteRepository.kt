package com.margento.sampleapp.data.remote

import com.margento.sampleapp.data.remote.request.SampleRequest
import com.margento.sampleapp.data.remote.response.SampleResponse
import kotlinx.coroutines.Deferred
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteRepository : RemoteRepositoryContract, KoinComponent {

    val service : ApiService by inject()

    companion object {
        val TAG : String = RemoteRepository::class.java.simpleName
    }

    override fun getSampleData(sampleRequest: SampleRequest) : Deferred<SampleResponse> {
        return service.getSampleData(sampleRequest)
    }

}