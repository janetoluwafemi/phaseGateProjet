package com.africa.semicolon.data.dtos.requests;

import lombok.Data;

@Data
public class UserCanShareContactRequest {
    private String phoneNumber;
    private String senderId;
    private String receiverEmail;
}
