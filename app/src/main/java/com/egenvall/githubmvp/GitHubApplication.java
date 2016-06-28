package com.egenvall.githubmvp;

import android.app.Application;

import com.egenvall.githubmvp.di.component.DaggerGitHubComponent;
import com.egenvall.githubmvp.di.component.GitHubComponent;
import com.egenvall.githubmvp.di.module.GitHubModule;

public class GitHubApplication extends Application {

    private GitHubComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setUp();
    }

    /**
     * Sets up the Dagger2 Dependency graph for the Application
     */
    private void setUp() {


        component = DaggerGitHubComponent.builder()
                .gitHubModule(new GitHubModule(this))
                .build();
        component.inject(this);
    }

    public GitHubComponent component() {
        return component;
    }
}
