package com.egenvall.githubmvp.network;

import com.egenvall.githubmvp.model.GitHubRepo;

import java.util.List;

import rx.Observable;

/**
 * Created by kWall on 2016-06-28.
 */

public interface Repository {

    Observable<List<GitHubRepo>> getRepositoriesForUser(String username);
}
