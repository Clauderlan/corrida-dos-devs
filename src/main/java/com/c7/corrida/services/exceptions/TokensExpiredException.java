package com.c7.corrida.services.exceptions;

public class TokensExpiredException extends RuntimeException{
    public TokensExpiredException(String message){
        super(message);
    }

}
