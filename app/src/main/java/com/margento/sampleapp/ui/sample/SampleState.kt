package com.margento.sampleapp.ui.sample

import com.margento.sampleapp.model.Sample

sealed class SampleState {

    class ShowSampleData(val sampleList : List<Sample>) : SampleState()

    class ShowError(val code : Int) : SampleState()

}