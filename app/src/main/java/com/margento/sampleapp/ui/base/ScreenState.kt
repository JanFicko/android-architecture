package com.margento.sampleapp.ui.base

sealed class ScreenState <out T> {

    class Loading(val active : Boolean) : ScreenState<Nothing>()
    class Render<T>(val renderState : T) : ScreenState<T>()

}