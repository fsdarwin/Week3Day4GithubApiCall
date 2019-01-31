package com.example.githubapicall;

import com.example.githubapicall.pojos.repos.Repos;

public class RepoEvent {

    public final Repos reposMessage;

    public RepoEvent(Repos reposMessage) {
        this.reposMessage = reposMessage;
    }

    public Repos getReposMessage() {
        return reposMessage;
    }
}
