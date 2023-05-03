package com.c7.corrida.entities;

import com.c7.corrida.entities.enums.CategoryRule;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rule;
    private String name;

    public Category(){}

    public Category(Long id, CategoryRule rule, String name) {
        this.id = id;
        setCategoryRule(rule);
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
