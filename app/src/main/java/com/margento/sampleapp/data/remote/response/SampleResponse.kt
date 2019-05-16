package com.margento.sampleapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.margento.sampleapp.model.Sample

data class SampleResponse(
    @SerializedName("sampleList")
    @Expose
    val sampleList : List<Sample>
) : BaseResponse()