package com.c7.corrida.entities.auxiliary;

import com.c7.corrida.entities.SocialNetwork;
import com.c7.corrida.entities.User;

public class AuxiliarySocialNetwork {

    private User user;

    private String socialName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }
}
