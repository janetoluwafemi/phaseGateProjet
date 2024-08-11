package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
