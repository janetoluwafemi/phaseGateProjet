package com.africa.semicolon.data.exceptions;

public class UserNotLoggedInException extends RuntimeException{
    public UserNotLoggedInException(String message){
        super(message);
    }
}
