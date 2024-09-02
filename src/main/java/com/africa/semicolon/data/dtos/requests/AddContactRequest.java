package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AddContactRequest {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userEmail;
}
