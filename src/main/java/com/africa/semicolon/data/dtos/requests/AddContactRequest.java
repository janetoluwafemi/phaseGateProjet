package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class AddContactRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userEmail;
}
