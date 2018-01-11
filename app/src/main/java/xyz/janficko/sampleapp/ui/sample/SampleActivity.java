package xyz.janficko.sampleapp.ui.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import xyz.janficko.sampleapp.R;
import xyz.janficko.sampleapp.commons.Animation;
import xyz.janficko.sampleapp.data.sample.SampleRepository;
import xyz.janficko.sampleapp.ui.BaseActivity;

public class SampleActivity extends BaseActivity {

    private SampleFragment sampleFragment;

    public static void start(Context context) {
        Intent starter = new Intent(context, SampleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_base;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToNextFragment(null);
    }

    @Override
    public void goToNextFragment(Fragment fragment) {
        super.goToNextFragment(fragment);
        if(fragment == null){
            sampleFragment = SampleFragment.newInstance();
            new SamplePresenter(
                sampleFragment,
                new SampleRepository()
            );
            moveToNextFragment(sampleFragment, Animation.NONE);
        }
    }

    @Override
    public void goToPreviousFragment(Fragment fragment) {
        super.goToPreviousFragment(fragment);
        if(fragment instanceof SampleFragment){
            /* Handle further user navigation. */
        }
    }
}
