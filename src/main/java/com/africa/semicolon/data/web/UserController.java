package com.africa.semicolon.data.web;

import com.africa.semicolon.data.dtos.requests.UserLogInRequest;
import com.africa.semicolon.data.dtos.requests.UserRegisterRequest;
import com.africa.semicolon.data.dtos.responses.ApiResponse;
import com.africa.semicolon.data.dtos.responses.UserLogInResponse;
import com.africa.semicolon.data.dtos.responses.UserRegisterResponse;
import com.africa.semicolon.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequestMapping
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true,userRegisterResponse), HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("logIn")
    public ResponseEntity<?> userLogIn(@RequestBody UserLogInRequest userLogInRequest){
        try {
            UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
            return new ResponseEntity<>(new ApiResponse(true,userLogInResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
