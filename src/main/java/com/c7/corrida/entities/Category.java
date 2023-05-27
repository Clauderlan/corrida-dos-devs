package com.c7.corrida.entities;

import com.c7.corrida.entities.enums.CategoryRule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rule;

    @JsonIgnore
    @OneToMany
    private List<User> users = new ArrayList<>();
    public Category(){}

    public Category(Long id, CategoryRule rule) {
        this.id = id;
        setCategoryRule(rule);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryRule getCategoryRule() {
        return CategoryRule.valueOf(rule);
    }

    public void setCategoryRule(CategoryRule rule) {
        if(rule != null){
            this.rule = rule.getCode();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getAuthority() {
        return getCategoryRule().name();
    }
}
