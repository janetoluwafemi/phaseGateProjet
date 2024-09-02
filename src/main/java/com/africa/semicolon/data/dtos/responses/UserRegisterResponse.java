package com.africa.semicolon.data.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRegisterResponse {
    private String message;
    @Id
    private String id;
}
