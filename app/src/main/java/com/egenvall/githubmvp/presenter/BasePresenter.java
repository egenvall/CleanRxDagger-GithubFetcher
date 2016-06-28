package com.egenvall.githubmvp.presenter;
public interface BasePresenter<V> {

    void onCreate();
    void onStop();

    void attachView(V view);

}
