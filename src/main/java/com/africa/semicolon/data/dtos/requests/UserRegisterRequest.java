package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
