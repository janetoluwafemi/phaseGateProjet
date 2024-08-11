package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class UserRemoveContactRequest {
    private String email;
    private String userEmail;
    private String phoneNumber;
}
