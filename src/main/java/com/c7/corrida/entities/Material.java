package com.c7.corrida.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_material")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String urlImage;
    private String idealFor;
    private String detailedInformation;
    private String shortInformation;

    public Material(){}

    public Material(Long id, String title, String urlImage, String idealFor, String detailedInformation, String shortInformation) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.idealFor = idealFor;
        this.detailedInformation = detailedInformation;
        this.shortInformation = shortInformation;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getIdealFor() {
        return idealFor;
    }

    public void setIdealFor(String idealFor) {
        this.idealFor = idealFor;
    }

    public String getDetailedInformation() {
        return detailedInformation;
    }

    public void setDetailedInformation(String detailedInformation) {
        this.detailedInformation = detailedInformation;
    }

    public String getShortInformation() {
        return shortInformation;
    }

    public void setShortInformation(String shortInformation) {
        this.shortInformation = shortInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
