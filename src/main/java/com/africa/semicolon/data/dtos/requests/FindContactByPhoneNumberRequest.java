package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class FindContactByPhoneNumberRequest {
    private String phoneNumber;
}
