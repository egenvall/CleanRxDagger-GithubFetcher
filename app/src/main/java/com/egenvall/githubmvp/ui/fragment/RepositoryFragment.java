package com.egenvall.githubmvp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.egenvall.githubmvp.GitHubApplication;
import com.egenvall.githubmvp.R;
import com.egenvall.githubmvp.adapter.RepositoryAdapter;
import com.egenvall.githubmvp.di.component.DaggerGitHubComponent;
import com.egenvall.githubmvp.di.module.GitHubModule;
import com.egenvall.githubmvp.model.GitHubRepo;
import com.egenvall.githubmvp.presenter.RepositoryPresenter;
import com.egenvall.githubmvp.ui.view.IRepositoriesView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kWall on 2016-06-28.
 */

public class RepositoryFragment extends BaseFragment implements IRepositoriesView {


    @Inject
    RepositoryPresenter repositoryPresenter;
    @Bind(R.id.rv_loclist)
    RecyclerView recyclerView;
    @Bind(R.id.pb_loclist)
    ProgressBar progressBar;
    @Bind(R.id.textView3)
    TextView errorText;
    private LinearLayoutManager layoutManager;
    private RepositoryAdapter listAdapter;
    private Activity activity;


    public static RepositoryFragment newInstance() {
        RepositoryFragment fragment = new RepositoryFragment();
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        repositoryPresenter.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repositories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initDependencyInjector();
        initPresenter();
        //getCurrentLocation();
    }

    @Override
    public void showRepositories(List<GitHubRepo> repositoryList) {
        this.listAdapter.setRepositoryList(repositoryList);
        this.listAdapter.notifyDataSetChanged();
    }
    @Override
    public void showErrorText(String errorText){
        this.errorText.setText(errorText);

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void initView() {
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new RepositoryAdapter(activity);
        recyclerView.setAdapter(listAdapter);
    }

    private void initDependencyInjector() {
        GitHubApplication gitHubApplication = (GitHubApplication) activity.getApplication();
        DaggerGitHubComponent.builder()
                .gitHubModule(new GitHubModule(gitHubApplication))
                .build()
                .inject(this);

    }

    //TODO Add Search Field and not hardcoding search
    private void initPresenter() {
        repositoryPresenter.attachView(this);
        repositoryPresenter.onCreate();
        repositoryPresenter.getRepositories("egenvall");
    }
}
