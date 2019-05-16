package com.margento.sampleapp.ui.main

import com.margento.sampleapp.SampleApp
import com.margento.sampleapp.ui.base.BaseViewModel

class MainViewModel(
    sampleApp : SampleApp
) : BaseViewModel<MainState>(sampleApp) {

    init {
        openSampleFragment()
    }

    fun openSampleFragment() {
        postScreenState(MainState.OpenSample())
    }

}