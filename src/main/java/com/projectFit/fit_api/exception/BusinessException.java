package com.projectFit.fit_api.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensaje){
        super(mensaje);
    }
}
