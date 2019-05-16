package com.margento.sampleapp.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.margento.sampleapp.common.Constants
import com.margento.sampleapp.ui.main.MainActivity
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(Constants.SPLASH_WAIT_SECONDS))
            withContext(Dispatchers.Main) {
                MainActivity.start(this@SplashActivity)
            }
        }
    }
}