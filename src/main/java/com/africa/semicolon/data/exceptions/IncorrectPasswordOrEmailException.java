package com.africa.semicolon.data.exceptions;

public class IncorrectPasswordOrEmailException extends RuntimeException{
    public IncorrectPasswordOrEmailException(String message){
        super(message);
    }
}
