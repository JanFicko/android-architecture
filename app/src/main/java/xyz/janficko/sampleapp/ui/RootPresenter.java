package xyz.janficko.sampleapp.ui;

import io.reactivex.disposables.CompositeDisposable;

/**
 Created by Jani Trstenjak.
 */
public abstract class RootPresenter<V extends BaseView> {

    private V view;
    public CompositeDisposable compositeDisposable;

    public RootPresenter(V view){
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void onNoInternet() {
        view.showNoInternet();
    }
}
