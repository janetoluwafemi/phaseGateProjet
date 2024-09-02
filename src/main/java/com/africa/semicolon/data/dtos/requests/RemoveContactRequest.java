package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class RemoveContactRequest {
    @Id
    private String id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String userEmail;
}
