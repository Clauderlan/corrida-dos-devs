package com.c7.corrida.entities.auxiliary;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.User;

import java.io.Serializable;


public class AuxiliaryChallengeResponse implements Serializable {

    private User user;

    private Challenge challenge;

    private Boolean rated = false;

    private String responseLink;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Boolean getRated() {
        return rated;
    }

    public void setRated(Boolean rated) {
        this.rated = rated;
    }

    public String getResponseLink() {
        return responseLink;
    }

    public void setResponseLink(String responseLink) {
        this.responseLink = responseLink;
    }

}
