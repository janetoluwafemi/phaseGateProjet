package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserLogInRequest {
    private String email;
    private String password;
    @Id
    private String id;
}
