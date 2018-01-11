package xyz.janficko.sampleapp.ui;

/**
 Created by Jani Trstenjak.
 */
public abstract class RootPresenter<V extends BaseView> {

    private V view;

    public RootPresenter(V view){
        this.view = view;
    }

    public void onNoInternet() {
        view.showNoInternet();
    }
}
