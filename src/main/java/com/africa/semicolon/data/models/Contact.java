package com.africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("contactrepo")
public class Contact {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
