package com.egenvall.githubmvp.network;

import com.egenvall.githubmvp.model.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kWall on 2016-06-28.
 */

public interface GitHubApiService {

    @GET("users/{username}/repos")
    Observable<List<GitHubRepo>> getRepositories(@Path("username") String username);
}
