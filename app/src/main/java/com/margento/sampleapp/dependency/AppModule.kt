package com.margento.sampleapp.dependency

import android.content.Context
import android.content.SharedPreferences
import com.margento.sampleapp.SampleApp
import com.margento.sampleapp.common.Constants
import com.margento.sampleapp.data.local.shared_preferences.SharedPreferencesContract
import com.margento.sampleapp.data.local.shared_preferences.SharedPreferencesStorage
import com.margento.sampleapp.data.remote.RemoteRepository
import com.margento.sampleapp.data.remote.RemoteRepositoryContract
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val appModule = module {

    factory { SampleApp.instance }

    singleBy<RemoteRepositoryContract, RemoteRepository>()

    singleBy<SharedPreferencesContract, SharedPreferencesStorage>()
    single<SharedPreferences> { androidContext().getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE) }

}