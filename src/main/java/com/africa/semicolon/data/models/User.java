package com.africa.semicolon.data.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isLogIn;
    @DBRef
    private List<Contact> contactList = new ArrayList<>();
}
