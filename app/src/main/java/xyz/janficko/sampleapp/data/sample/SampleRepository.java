package xyz.janficko.sampleapp.data.sample;

import android.app.Activity;

import xyz.janficko.sampleapp.data.BaseRepository;
import xyz.janficko.sampleapp.model.Sample;

public class SampleRepository extends BaseRepository<Sample> implements SampleDataSource {

    private SampleDataSource.LoadSampleCallback presenter;

    public SampleRepository(Activity activity){
        setActivity(activity);
    }

    // TODO: Generify this.
    public void setPresenter(SampleDataSource.LoadSampleCallback presenter){
        this.presenter = presenter;
    }

    @Override
    public void getSampleDataFromService() {
        SampleService sampleService = retrofitFactory.sampleService();
        getDataFromService(
                sampleService.getSampleData("sampleData"),
                presenter
        );
    }
}
