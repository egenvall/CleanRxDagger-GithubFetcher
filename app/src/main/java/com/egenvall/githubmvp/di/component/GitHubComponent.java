package com.egenvall.githubmvp.di.component;

import com.egenvall.githubmvp.GitHubApplication;
import com.egenvall.githubmvp.di.module.GitHubModule;
import com.egenvall.githubmvp.domain.GetUserRepositoriesUsecase;
import com.egenvall.githubmvp.network.Repository;
import com.egenvall.githubmvp.ui.fragment.RepositoryFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kWall on 2016-06-28.
 */
@Singleton
@Component(
        modules = {
                GitHubModule.class
        }
)
public interface GitHubComponent {


        void inject(GitHubApplication gitHubApplication);

        void inject(RepositoryFragment repositoryFragment);

        GetUserRepositoriesUsecase getUserRepositoriesUsecase();

        GitHubApplication application();

        Repository repository();


}
