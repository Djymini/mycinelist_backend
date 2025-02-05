package com.mycinelist.mycinelist_backend.exceptions;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(String message){
        super(message);
    }
}
