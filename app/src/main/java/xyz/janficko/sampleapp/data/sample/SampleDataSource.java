package xyz.janficko.sampleapp.data.sample;

import xyz.janficko.sampleapp.data.BaseDataSource;
import xyz.janficko.sampleapp.model.Sample;

public interface SampleDataSource {

    interface LoadSampleCallback extends BaseDataSource<Sample> {}

    void getSampleDataFromService();

}
