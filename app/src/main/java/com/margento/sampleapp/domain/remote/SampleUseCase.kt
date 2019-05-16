package com.margento.sampleapp.domain.remote

import com.margento.sampleapp.data.remote.RemoteRepositoryContract
import com.margento.sampleapp.data.remote.request.SampleRequest
import com.margento.sampleapp.data.remote.response.SampleResponse
import kotlinx.coroutines.Deferred

class SampleUseCase(remoteRepository: RemoteRepositoryContract) : BaseRemoteUseCase(remoteRepository) {

    fun getSampleData(sampleRequest: SampleRequest) : Deferred<SampleResponse> {
        return remoteRepository.getSampleData(sampleRequest)
    }

}