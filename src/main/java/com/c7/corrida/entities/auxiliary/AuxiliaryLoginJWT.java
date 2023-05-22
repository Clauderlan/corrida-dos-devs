package com.c7.corrida.entities.auxiliary;

public class AuxiliaryLoginJWT {

    private String jwt;

    public AuxiliaryLoginJWT(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
