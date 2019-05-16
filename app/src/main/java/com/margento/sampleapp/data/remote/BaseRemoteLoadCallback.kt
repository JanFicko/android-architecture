package com.margento.sampleapp.data.remote

import com.margento.sampleapp.data.remote.response.BaseResponse

interface BaseRemoteLoadCallback<R : BaseResponse> {
    fun onSuccess(body: R)
    fun onError(code: Int)
    fun onUnknownError()
    fun onNoInternet()
    fun onNoServer()
    fun onLoadIndicator(active: Boolean)
}