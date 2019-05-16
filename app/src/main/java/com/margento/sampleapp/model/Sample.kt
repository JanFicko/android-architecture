package com.margento.sampleapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sample(
    @SerializedName("sampleString")
    @Expose
    val sampleString : String
)