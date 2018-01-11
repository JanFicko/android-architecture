package xyz.janficko.sampleapp.data.sample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.janficko.sampleapp.model.Sample;

public interface SampleService {

    @GET("samplePath")
    Call<Sample> getSampleData(@Query("sampleQuery") String sampleQuery);

}
