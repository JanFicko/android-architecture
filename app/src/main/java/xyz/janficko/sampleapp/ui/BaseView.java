package xyz.janficko.sampleapp.ui;

/**
 Created by Jani Trstenjak.
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void showNoInternet();

}
