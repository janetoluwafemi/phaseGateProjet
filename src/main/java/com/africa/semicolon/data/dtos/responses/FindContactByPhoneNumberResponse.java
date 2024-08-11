package com.africa.semicolon.data.dtos.responses;

import lombok.Data;

@Data
public class FindContactByPhoneNumberResponse {
    private String message;
    private String phoneNumber;
    private String firstName;
}
