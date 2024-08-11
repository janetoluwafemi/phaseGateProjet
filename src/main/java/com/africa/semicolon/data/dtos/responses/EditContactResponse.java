package com.africa.semicolon.data.dtos.responses;

import lombok.Data;

@Data
public class EditContactResponse {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String message;
}
