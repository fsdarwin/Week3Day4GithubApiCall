package com.example.githubapicall;

import com.example.githubapicall.pojos.Profile;

public class MessageEvent {

    public final Profile profileMessage;

    public MessageEvent(Profile profileMessage) {
        this.profileMessage = profileMessage;
    }

    public Profile getProfileMessage() {
        return profileMessage;
    }
}