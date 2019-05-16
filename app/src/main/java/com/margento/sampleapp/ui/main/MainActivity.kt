package com.margento.sampleapp.ui.main

import android.content.Context
import android.content.Intent
import com.margento.sampleapp.R
import com.margento.sampleapp.ui.base.BaseViewModelActivity
import com.margento.sampleapp.ui.sample.SampleFragment
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : BaseViewModelActivity<MainState, MainViewModel>() {

    override val viewmodel : MainViewModel by viewModel()
    //private val sharedPreferences : SharedPreferencesContract by inject()

    override val layoutResId : Int
        get() = R.layout.activity_main

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun processRenderState(renderState: MainState) {
        when (renderState) {
            is MainState.OpenSample -> openSample()
        }
    }

    fun openSample() {
        moveToNextFragment(SampleFragment())
    }

}