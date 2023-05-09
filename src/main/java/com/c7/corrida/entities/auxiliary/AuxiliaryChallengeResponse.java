package com.c7.corrida.entities.auxiliary;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.User;

import java.io.Serializable;


public class AuxiliaryChallengeResponse implements Serializable {

    private Long userId;
    private Long challengeId;

    private Boolean rated = false;

    private String responseLink;

    public AuxiliaryChallengeResponse(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
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
