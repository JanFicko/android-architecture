package xyz.janficko.sampleapp.ui.sample;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import xyz.janficko.sampleapp.R;
import xyz.janficko.sampleapp.model.Sample;
import xyz.janficko.sampleapp.ui.BaseFragment;

public class SampleFragment extends BaseFragment implements SampleContract.View {

    private SampleContract.Presenter presenter;

    @BindView(R.id.tv_sample_title)
    TextView tvSampleTitle;

    public static SampleFragment newInstance() {
        Bundle args = new Bundle();
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_sample;
    }

    @Override
    public void setPresenter(SampleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            /* Show loader. */
        } else {
            /* Hide loader. */
        }
    }

    @Override
    public void showSampleView(Sample sample) {
        /*
         * Fill view with data.
         * tvSampleTitle.setText(sample.toString())
         */
    }

    @Override
    public void showErrorMessage() {
        /* Let user know there was an error when fetching data. */
    }

    @Override
    public void showNoInternet() {
        /* Let user know he has no internet. */
    }
}
