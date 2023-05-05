package com.c7.corrida.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long e){
        super("Not found. Id : " + e);
    }
}
