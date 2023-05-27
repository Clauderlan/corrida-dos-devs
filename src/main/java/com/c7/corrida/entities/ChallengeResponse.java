package com.c7.corrida.entities;

import com.c7.corrida.entities.pk.ChallengeResponsePK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_challengeresponse")
public class ChallengeResponse implements Serializable {

    @EmbeddedId
    private ChallengeResponsePK id = new ChallengeResponsePK();
    private String responseLink;
    private Boolean rated = false;
    public ChallengeResponse(){}

    public ChallengeResponse(User user, Challenge challenge, String responseLink) {
        id.setUser(user);
        id.setChallenge(challenge);
        this.responseLink = responseLink;
    }

    @JsonIgnore
    public User getUser(){
        return id.getUser();
    }
    public void setUser(User user){
        id.setUser(user);
    }
    @JsonIgnore
    public Challenge getChallenge(){
        return id.getChallenge();
    }
    public void setChallenge(Challenge challenge){
        id.setChallenge(challenge);
    }
    public String getResponseLink() {
        return responseLink;
    }

    public void setResponseLink(String responseLink) {
        this.responseLink = responseLink;
    }

    public Boolean getRated() {
        return rated;
    }

    public void setRated(Boolean rated) {
        this.rated = rated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeResponse that = (ChallengeResponse) o;
        return Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
