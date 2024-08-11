package com.africa.semicolon.data.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ShareContactResponse {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
}
