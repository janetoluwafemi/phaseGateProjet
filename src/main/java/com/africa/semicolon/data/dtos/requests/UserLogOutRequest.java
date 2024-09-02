package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserLogOutRequest {
    private String email;
    @Id
    private String id;
}
