package com.c7.corrida.services.exceptions;

public class ResourceExistsException extends RuntimeException{
    public ResourceExistsException(String msg){
        super(msg + " already exists");
    }
}
