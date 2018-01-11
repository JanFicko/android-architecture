package xyz.janficko.sampleapp.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import xyz.janficko.sampleapp.commons.Constants;
import xyz.janficko.sampleapp.ui.BaseActivity;
import xyz.janficko.sampleapp.ui.sample.SampleActivity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_LOAD_WAIT = 2000;

    @Override
    protected int setLayoutResId() {
        return Constants.NO_LAYOUT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(
                () -> SampleActivity.start(this),
                SPLASH_LOAD_WAIT
        );
    }
}
