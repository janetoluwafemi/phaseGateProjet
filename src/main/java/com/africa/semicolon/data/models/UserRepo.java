package com.africa.semicolon.data.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findUserById(String id);
    User findUserByEmail(String email);
    User findUserByPhoneNumber(String number);
    boolean existsByEmail(String email);
}
