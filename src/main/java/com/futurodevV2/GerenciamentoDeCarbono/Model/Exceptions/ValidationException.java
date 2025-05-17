package com.futurodevV2.GerenciamentoDeCarbono.Model.Exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
