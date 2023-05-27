package com.c7.corrida.entities.enums;

public enum CategoryRule {
    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    private Integer code;
    private CategoryRule(int code){
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
    public static CategoryRule valueOf(int code){
        for(CategoryRule value : CategoryRule.values()){
            if (value.getCode() == code) return value;
        }
        throw new IllegalArgumentException("Invalid CategoryRule code.");
    }
}
