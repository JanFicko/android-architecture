package xyz.janficko.sampleapp.ui.sample;

import xyz.janficko.sampleapp.commons.ErrorCodes;
import xyz.janficko.sampleapp.data.sample.SampleDataSource;
import xyz.janficko.sampleapp.data.sample.SampleRepository;
import xyz.janficko.sampleapp.model.Sample;
import xyz.janficko.sampleapp.ui.RootPresenter;

public class SamplePresenter extends RootPresenter<SampleContract.View> implements SampleContract.Presenter, SampleDataSource.LoadSampleCallback {

    private SampleContract.View view;
    private SampleRepository repository;

    public SamplePresenter(SampleContract.View view, SampleRepository repository) {
        super(view);
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
        repository.setPresenter(this);
    }

    @Override
    public void start() {
        getSampleData();
    }

    @Override
    public void getSampleData() {
        view.setLoadingIndicator(true);
        repository.getSampleDataFromService();
    }

    @Override
    public void onSuccess(Sample body) {
        view.setLoadingIndicator(false);
        view.showSampleView(body);
    }

    @Override
    public void onError(int code) {
        view.setLoadingIndicator(false);
        switch(code){
            case ErrorCodes.UNKNOWN_ERROR:
            case ErrorCodes.SERVICE_ERROR:
            default:
                view.showErrorMessage();
                break;
        }
    }
}
