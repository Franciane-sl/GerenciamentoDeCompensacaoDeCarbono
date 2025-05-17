package com.futurodevV2.GerenciamentoDeCarbono.Model.Exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
