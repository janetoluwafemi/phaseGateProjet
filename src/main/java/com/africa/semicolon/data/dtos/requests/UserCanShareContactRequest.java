package com.africa.semicolon.data.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserCanShareContactRequest {
    private String phoneNumber;
    @Id
    private String id;
    private String senderEmail;
    private String receiverEmail;
}
