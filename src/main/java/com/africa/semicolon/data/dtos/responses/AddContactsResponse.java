package com.africa.semicolon.data.dtos.responses;

import lombok.Data;

@Data
public class AddContactsResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
}
