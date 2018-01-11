package xyz.janficko.sampleapp.data.sample;

import xyz.janficko.sampleapp.data.BaseRepository;
import xyz.janficko.sampleapp.model.Sample;

public class SampleRepository extends BaseRepository<Sample> implements SampleDataSource {

    private SampleDataSource.LoadSampleCallback presenter;

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
