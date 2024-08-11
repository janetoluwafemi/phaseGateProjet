package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class EditContactRequest {
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
