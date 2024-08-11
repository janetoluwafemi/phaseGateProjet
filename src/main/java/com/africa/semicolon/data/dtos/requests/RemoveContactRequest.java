package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveContactRequest {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String userEmail;
}
