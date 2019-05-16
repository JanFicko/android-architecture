package com.margento.sampleapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.margento.sampleapp.R
import com.margento.sampleapp.util.inTransaction
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseViewModelActivity<ST, VM : BaseViewModel<ST>> : AppCompatActivity() {

    protected var TAG = ""
    abstract val viewmodel : VM

    @get : LayoutRes
    protected abstract val layoutResId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TAG = javaClass.simpleName

        if(layoutResId != 0) {
            setContentView(layoutResId)
        }

        viewmodel.screenState.observe(::getLifecycle, ::updateUi)
    }

    private fun updateUi(screenState : ScreenState<ST>) = when (screenState) {
        is ScreenState.Render -> processRenderState(screenState.renderState)
        is ScreenState.Loading -> showLoadingIndicator(screenState.active)
    }

    abstract fun processRenderState(renderState : ST)

    fun moveToNextFragment(fragment : Fragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.content_container, fragment)
        }
    }

    fun showLoadingIndicator(active : Boolean) {
        if (active) {
            loader.visibility = View.VISIBLE
        } else {
            loader.visibility = View.GONE
        }
    }

}