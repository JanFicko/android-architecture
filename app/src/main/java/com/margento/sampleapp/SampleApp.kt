package com.margento.sampleapp

import android.app.Application
import com.margento.sampleapp.dependency.appModule
import com.margento.sampleapp.dependency.networkModule
import com.margento.sampleapp.dependency.projectModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SampleApp : Application() {

    companion object {
        lateinit var instance : SampleApp
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SampleApp)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    projectModule
                )
            )
        }
        instance = this
    }

}