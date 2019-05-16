package com.margento.sampleapp.ui.sample

import com.margento.sampleapp.R
import com.margento.sampleapp.ui.base.BaseViewModelFragment
import com.margento.sampleapp.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_sample.*
import org.koin.androidx.viewmodel.ext.sharedViewModel
import org.koin.androidx.viewmodel.ext.viewModel
import kotlin.contracts.ExperimentalContracts

class SampleFragment : BaseViewModelFragment<SampleState, SampleViewModel>() {

    override val viewmodel : SampleViewModel by viewModel()
    private val sharedviewmodel : MainViewModel by sharedViewModel()

    override val layoutResId : Int
        get() = R.layout.fragment_sample

    override fun processRenderState(renderState: SampleState) {
        when (renderState) {
            is SampleState.ShowSampleData -> {
                tv_sample.text = renderState.sampleList.get(0).sampleString
            }
            is SampleState.ShowError -> {
                when (renderState.code) { }
            }
        }
    }
}