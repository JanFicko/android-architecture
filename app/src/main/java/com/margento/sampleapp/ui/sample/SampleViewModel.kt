package com.margento.sampleapp.ui.sample

import com.margento.sampleapp.SampleApp
import com.margento.sampleapp.data.remote.request.SampleRequest
import com.margento.sampleapp.data.remote.response.SampleResponse
import com.margento.sampleapp.domain.remote.SampleUseCase
import com.margento.sampleapp.model.Sample
import com.margento.sampleapp.ui.base.BaseViewModel
import com.margento.sampleapp.ui.base.ScreenState

class SampleViewModel constructor(
    sampleApp : SampleApp,
    private val sampleUseCase : SampleUseCase
) : BaseViewModel<SampleState>(sampleApp){

    init {
        sampleRemoteCall()
    }

    fun sampleRemoteCall () {
        val sample = Sample("Lorem Ipsum")

        getDataFromWeb(
            sampleUseCase.getSampleData(
                SampleRequest(sample)
            ),
            object : BaseViewModel<SampleState>.RemoteRepositoryCallback<SampleResponse>() {
                override fun onSuccess(body : SampleResponse) {
                    postScreenState(SampleState.ShowSampleData(body.sampleList))
                }

            }
        )
    }

}