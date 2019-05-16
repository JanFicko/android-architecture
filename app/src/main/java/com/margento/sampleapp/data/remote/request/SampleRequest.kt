package com.margento.sampleapp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.margento.sampleapp.model.Sample

data class SampleRequest(
    @SerializedName("sample")
    @Expose
    val sample : Sample
)