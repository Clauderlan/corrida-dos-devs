package com.c7.corrida.entities;

import com.c7.corrida.entities.contents.ChallengeContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_challenge")
public class Challenge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String bio;
    private String requirements;
    private Instant deadline;
    private String urlImage;
    private Integer points;

    @JoinColumn(name = "id_challenge")
    @OneToMany
    private List<ChallengeContent> challengeContent = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<ChallengeResponse> challengeResponse = new ArrayList<>();

    public Challenge(){}

    public Challenge(Long id, String title, String bio, String requirements, Instant deadline, String urlImage, Integer points) {
        this.id = id;
        this.title = title;
        this.bio = bio;
        this.requirements = requirements;
        this.deadline = deadline;
        this.urlImage = urlImage;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Instant getDeadline() {
        return deadline;
    }

    public void setDeadline(Instant deadline) {
        this.deadline = deadline;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<ChallengeContent> getChallengeContent() {
        return challengeContent;
    }

    public List<ChallengeResponse> getChallengeResponse() {
        return challengeResponse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
