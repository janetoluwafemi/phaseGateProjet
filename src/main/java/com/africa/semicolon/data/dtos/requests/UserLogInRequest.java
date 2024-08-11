package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class UserLogInRequest {
    private String email;
    private String password;
}
