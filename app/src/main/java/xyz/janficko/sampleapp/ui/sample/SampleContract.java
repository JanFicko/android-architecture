package xyz.janficko.sampleapp.ui.sample;

import xyz.janficko.sampleapp.model.Sample;
import xyz.janficko.sampleapp.ui.BasePresenter;
import xyz.janficko.sampleapp.ui.BaseView;

public interface SampleContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showSampleView(Sample sample);
        void showErrorMessage();
    }

    interface Presenter extends BasePresenter {
        void getSampleData();
    }

}
