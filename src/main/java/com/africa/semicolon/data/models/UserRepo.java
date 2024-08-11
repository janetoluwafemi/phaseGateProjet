package com.africa.semicolon.data.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findUserByEmail(String email);
    User findUserByPhoneNumber(String number);
    boolean existsByEmail(String email);
}
