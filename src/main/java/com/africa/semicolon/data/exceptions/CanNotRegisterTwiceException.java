package com.africa.semicolon.data.exceptions;

public class CanNotRegisterTwiceException extends RuntimeException{
    public CanNotRegisterTwiceException(String message){
        super(message);
    }
}
