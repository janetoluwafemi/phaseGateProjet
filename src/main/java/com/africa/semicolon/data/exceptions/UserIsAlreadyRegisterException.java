package com.africa.semicolon.data.exceptions;

public class UserIsAlreadyRegisterException extends RuntimeException{
    public UserIsAlreadyRegisterException(String message){
        super(message);
    }
}
