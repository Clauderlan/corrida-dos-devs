package com.c7.corrida.entities;


import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer rankPoints;
    private String bio;

    public User(){}

    public User(Long id,String name, String password, String email, Integer rankPoints, String bio) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.rankPoints = rankPoints;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRankPoints() {
        return rankPoints;
    }

    public void setRankPoints(Integer rankPoints) {
        this.rankPoints = rankPoints;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
