package com.egenvall.githubmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.egenvall.githubmvp.R;
import com.egenvall.githubmvp.model.GitHubRepo;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryListHolder> {

    private Context context;
    private static List<GitHubRepo> repositoryList;

    public RepositoryAdapter(Context context) {
        this.context = context;
    }

    public void setRepositoryList(List<GitHubRepo> repositoryList){
        this.repositoryList = repositoryList;
    }
    @Override
    public RepositoryListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_repository_list_item, parent, false);
        return new RepositoryListHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryListHolder holder, int position) {
        GitHubRepo movie = repositoryList.get(position);
        bindRepositoryListHolder(movie, holder);
    }

    @Override
    public int getItemCount() {
        return repositoryList == null ? 0 : repositoryList.size();
    }

    private void bindRepositoryListHolder(GitHubRepo repo, RepositoryListHolder holder) {
        holder.repositoryTitle.setText(repo.getName());
        holder.repositoryDesc.setText(repo.getDescription());
    }


    static class RepositoryListHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.repository_title) TextView repositoryTitle;
        @Bind(R.id.repository_title_sub) TextView repositoryDesc;

        public RepositoryListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
