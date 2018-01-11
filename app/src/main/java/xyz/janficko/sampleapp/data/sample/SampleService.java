package xyz.janficko.sampleapp.data.sample;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.janficko.sampleapp.model.Sample;

public interface SampleService {

    @GET("samplePath")
    Single<Sample> getSampleData(@Query("sampleQuery") String sampleQuery);

}
