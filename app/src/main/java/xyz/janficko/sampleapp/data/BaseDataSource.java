package xyz.janficko.sampleapp.data;

import xyz.janficko.sampleapp.model.BaseResponse;

public interface BaseDataSource<R extends BaseResponse> {

    void onSuccess(R body);
    void onError(int code);
    void onNoInternet();

}
