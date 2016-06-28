package com.egenvall.githubmvp.ui.view;


import com.egenvall.githubmvp.model.GitHubRepo;
import java.util.List;
public interface IRepositoriesView extends IView {
    void showRepositories(List<GitHubRepo> movieList);

    void showLoading();

    void hideLoading();

    void showErrorText(String errorText);
}
