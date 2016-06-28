package com.egenvall.githubmvp.network;

import com.egenvall.githubmvp.model.GitHubRepo;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kWall on 2016-06-28.
 */

public class RestDataSource implements Repository {

    private GitHubApiService gitHubApiService;

    @Inject
    public RestDataSource(GitHubApiService service){
        this.gitHubApiService = service;
    }

    @Override
    public Observable<List<GitHubRepo>> getRepositoriesForUser(String username){
        return gitHubApiService.getRepositories(username);
    }
}
