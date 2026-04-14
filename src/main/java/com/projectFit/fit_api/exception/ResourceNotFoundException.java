package com.projectFit.fit_api.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
