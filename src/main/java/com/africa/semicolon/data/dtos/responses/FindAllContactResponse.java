package com.africa.semicolon.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindAllContactResponse {
    private String message;
    private String firstName;
    private String phoneNumber;
    private String lastName;
}
