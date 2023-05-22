package com.c7.corrida.services.exceptions;

public class DTOException {

    private Integer status;
    private String message;

    public DTOException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
