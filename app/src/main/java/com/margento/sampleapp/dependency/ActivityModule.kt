package com.margento.sampleapp.dependency

import com.margento.sampleapp.domain.remote.SampleUseCase
import com.margento.sampleapp.ui.main.MainViewModel
import com.margento.sampleapp.ui.sample.SampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val projectModule = module {

    factory<SampleUseCase>()

    viewModel { MainViewModel(get()) }
    viewModel { SampleViewModel(get(), get()) }

}