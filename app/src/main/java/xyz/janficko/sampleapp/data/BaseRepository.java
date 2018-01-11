package xyz.janficko.sampleapp.data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.janficko.sampleapp.commons.ErrorCodes;
import xyz.janficko.sampleapp.model.BaseResponse;

/**
 Created by Jani Trstenjak.
 */
public class BaseRepository<T extends BaseResponse> {

    protected RetrofitFactory retrofitFactory = new RetrofitFactory();
    private Activity activity;

    // TODO: Generify this.
    protected void setActivity(Activity activity){
        this.activity = activity;
    }

    protected void getDataFromService(Call<T> call, final BaseDataSource<T> dataSource) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                activity.runOnUiThread(() -> {
                    dataSource.onSuccess(response.body());
                });
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                activity.runOnUiThread(() -> {
                    if (t instanceof IOException) {
                        dataSource.onNoInternet();
                    }
                    else {
                        dataSource.onError(ErrorCodes.SERVICE_ERROR);
                    }
                });
            }
        });
    }

}
