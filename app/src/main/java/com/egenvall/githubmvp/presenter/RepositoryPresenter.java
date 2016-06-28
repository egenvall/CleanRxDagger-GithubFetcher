package com.egenvall.githubmvp.presenter;


import android.util.Log;

import com.egenvall.githubmvp.domain.GetUserRepositoriesUsecase;
import com.egenvall.githubmvp.model.GitHubRepo;
import com.egenvall.githubmvp.ui.view.IRepositoriesView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/*Implementation of the event from fragment -> Presenter -> UseCase*/

public class RepositoryPresenter implements BasePresenter<IRepositoriesView> {
    private Subscription getRepositoriesSubscription;
    private GetUserRepositoriesUsecase getUserRepositoriesUsecase;
    private IRepositoriesView repositoryView;
    private String TAG = "RepositoryPresenter";

    @Inject
    public RepositoryPresenter(GetUserRepositoriesUsecase repositoryUsecase) {
        this.getUserRepositoriesUsecase = repositoryUsecase;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStop(){
        if (getRepositoriesSubscription!= null && !getRepositoriesSubscription.isUnsubscribed()) {
            getRepositoriesSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(IRepositoriesView view) {
        this.repositoryView =  view;
    }

    public void getRepositories(String username){
        getUserRepositoriesUsecase.setSearchTerm(username);
        repositoryView.showLoading();
        getRepositoriesSubscription = getUserRepositoriesUsecase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        //If we recieve an Observable<List<GitHubRepo>>
                        // Call showRepositories (in fragment through the interface with that list
                        repositories -> {
                            repositoryView.showRepositories(repositories);
                            repositoryView.hideLoading();
                        },

                        //If we don't retrieve a successfull response, for example 404 or 401, print
                        //error message
                        error -> repositoryView.showErrorText(error.getMessage())
                );
    }
}
