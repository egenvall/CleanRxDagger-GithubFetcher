package com.egenvall.githubmvp.domain;

import com.egenvall.githubmvp.model.GitHubRepo;
import com.egenvall.githubmvp.network.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by kWall on 2016-06-28.
 */

public class GetUserRepositoriesUsecase implements Usecase<List<GitHubRepo>> {
    private Repository repository;
    private String username;
    public GetUserRepositoriesUsecase(Repository repository){
        this.repository = repository;

    }
    public void setSearchTerm(String searchTerm){
        this.username = searchTerm;

    }
    @Override
    public Observable<List<GitHubRepo>> execute(){
        return repository.getRepositoriesForUser(username);
    }
}

