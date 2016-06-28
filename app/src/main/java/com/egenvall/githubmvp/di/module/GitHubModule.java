package com.egenvall.githubmvp.di.module;

import com.egenvall.githubmvp.GitHubApplication;
import com.egenvall.githubmvp.di.ActivityScope;
import com.egenvall.githubmvp.domain.GetUserRepositoriesUsecase;
import com.egenvall.githubmvp.network.GitHubApiService;
import com.egenvall.githubmvp.network.Repository;
import com.egenvall.githubmvp.network.RestDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kWall on 2016-06-28.
 */

@Module
public class GitHubModule {

    private GitHubApplication gitHubApplication;

    public GitHubModule(GitHubApplication gitHubApplication) {
        this.gitHubApplication = gitHubApplication;
    }

    @Provides
    @Singleton
    GitHubApplication provideApplication() {
        return gitHubApplication;
    }

    /**
     * Logging Levels for debugging: BODY, HEADER
     * @return Retrofit instance if it exists, otherwise creates one
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GitHubApiService provideApiService(Retrofit retrofit){
        return retrofit.create(GitHubApiService.class);
    }


    @Provides @Singleton
    public Repository provideRestDataSource(GitHubApiService service){
        return new RestDataSource(service);
    }


    @Provides
    GetUserRepositoriesUsecase provideGetUserRepositoriesUsecase (Repository repository) {
        return new GetUserRepositoriesUsecase(repository);
    }
}
