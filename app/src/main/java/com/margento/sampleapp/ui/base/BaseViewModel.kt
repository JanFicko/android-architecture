package com.margento.sampleapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.margento.sampleapp.SampleApp
import com.margento.sampleapp.common.ErrorCodes
import com.margento.sampleapp.common.ResponseState
import com.margento.sampleapp.data.remote.BaseRemoteLoadCallback
import com.margento.sampleapp.data.remote.response.BaseResponse
import com.margento.sampleapp.dataholder.SingleLiveData
import com.margento.sampleapp.util.printError
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.contracts.ExperimentalContracts
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ST> constructor(val sampleApp : SampleApp) : ViewModel(), CoroutineScope, KoinComponent {

    private lateinit var _screenState : MutableLiveData<ScreenState<ST>>
    private lateinit var _loadingState : MutableLiveData<ScreenState<ST>>
    private lateinit var job : Job

    val screenState : LiveData<ScreenState<ST>>
        get() {
            if (!::_screenState.isInitialized) {
                _screenState = MutableLiveData()
            }
            return _screenState
        }

    val loadingState : LiveData<ScreenState<ST>>
        get() {
            if (!::_loadingState.isInitialized) {
                _loadingState = MutableLiveData()
            }
            return _loadingState
        }

    override val coroutineContext: CoroutineContext
        get() {
            /**
             *  ViewModel is tied to View's lifecycle. If you want to re-use View while switching between other View's
             *  onCleared is called and job gets cancelled. We've put isCancelled checker here to re-initialize job
             *  in case of View switching.
             */
            if (!::job.isInitialized || job.isCancelled) {
                job = Job()
            }
            return Dispatchers.Main + job
        }

    val scope : CoroutineScope by lazy {
        CoroutineScope(coroutineContext)
    }

    private val _responseEvent : SingleLiveData<ScreenState<ResponseState>> = SingleLiveData()

    val responseState : SingleLiveData<ScreenState<ResponseState>>
        get() = _responseEvent

    companion object {
        val TAG : String = BaseViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()

        if (::job.isInitialized) {
            job.cancel()
        }
    }

    protected fun postScreenState(screenState : ST) {
        if (!::_screenState.isInitialized) {
            _screenState = MutableLiveData()
        }
        _screenState.postValue(ScreenState.Render(screenState))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseResponse> getDataFromWeb(deferred : Deferred<BaseResponse>, dataSource : BaseRemoteLoadCallback<T>) {
        launch(Dispatchers.IO) {
            try {
                dataSource.onLoadIndicator(true)

                val result = deferred.await() as T

                dataSource.onLoadIndicator(false)

                if (result.code == ErrorCodes.RESPONSE_OK) {
                    dataSource.onSuccess(result)
                } else {
                    dataSource.onError(result.code)
                }
            } catch (e : HttpException) {
                e.message?.let { printError(TAG, it)}

                dataSource.onLoadIndicator(false)

                dataSource.onUnknownError()
            } catch (e : Throwable) {
                e.message?.let { printError(TAG, it)}

                dataSource.onLoadIndicator(false)
                if (e is UnknownHostException) {
                    dataSource.onNoInternet()
                } else if (e is IOException || e is ConnectException || e is SocketTimeoutException) {
                    dataSource.onNoServer()
                } else {
                    dataSource.onUnknownError()
                }
            }
        }
    }

    abstract inner class RemoteRepositoryCallback<T : BaseResponse> : BaseRemoteLoadCallback<T> {

        override fun onError(code: Int) {
            _responseEvent.postValue(ScreenState.Render(ResponseState.ERROR))
        }

        override fun onUnknownError() {
            _responseEvent.postValue(ScreenState.Render(ResponseState.UNKNOWN_ERROR))
        }

        override fun onNoInternet() {
            _responseEvent.postValue(ScreenState.Render(ResponseState.NO_INTERNET))
        }

        override fun onNoServer() {
            _responseEvent.postValue(ScreenState.Render(ResponseState.NO_SERVER))
        }

        override fun onLoadIndicator(active: Boolean) {
            if (active) {
                _responseEvent.postValue(ScreenState.Render(ResponseState.LOADING))
            } else {
                _responseEvent.postValue(ScreenState.Render(ResponseState.NOT_LOADING))
            }
        }

    }

}