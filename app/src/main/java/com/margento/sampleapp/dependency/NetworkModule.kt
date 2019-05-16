package com.margento.sampleapp.dependency

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.margento.sampleapp.BuildConfig
import com.margento.sampleapp.common.Constants
import com.margento.sampleapp.data.local.shared_preferences.SharedPreferencesContract
import com.margento.sampleapp.data.remote.ApiService
import com.margento.sampleapp.util.ApiLogger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val networkModule = module {

    single{ GsonBuilder().create() }

    single<Interceptor> {
        val logging = HttpLoggingInterceptor(ApiLogger())
        logging.level = HttpLoggingInterceptor.Level.BODY
        logging
    }

    single {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(get())
        httpClient.addInterceptor {  chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        httpClient.build()
    }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .callbackExecutor(Executors.newCachedThreadPool())
            .build()
    }

    single {
        (get() as Retrofit).create(ApiService::class.java)
    }

}