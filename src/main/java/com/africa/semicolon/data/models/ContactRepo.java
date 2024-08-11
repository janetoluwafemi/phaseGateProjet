package com.africa.semicolon.data.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepo extends MongoRepository<Contact, String> {
    Contact findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
