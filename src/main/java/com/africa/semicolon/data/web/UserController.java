package com.africa.semicolon.data.web;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("logOut")
    public ResponseEntity<?> userLogOut(@RequestBody UserLogOutRequest userLogOutRequest){
        try {
            UserLogOutResponse userLogOutResponse = userService.userLogOut(userLogOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, userLogOutResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("addContact")
    public ResponseEntity<?> userAddContact(@RequestBody AddContactRequest addContactRequest){
        try {
            AddContactsResponse addContactResponse = userService.userAddContact(addContactRequest);
            return new ResponseEntity<>(new ApiResponse(true,addContactResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("removeContact")
    public ResponseEntity<?> userRemoveContact(@RequestBody RemoveContactRequest removeContactRequest){
        try {
            RemoveContactResponse removeContactResponse = userService.userRemoveContact(removeContactRequest);
            return new ResponseEntity<>(new ApiResponse(true,removeContactResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("shareContact")
    public ResponseEntity<?> userCanShareContact(@RequestBody UserCanShareContactRequest userCanShareContactRequest){
        try {
            ShareContactResponse shareContactResponse = userService.userCanShareContact(userCanShareContactRequest);
            return new ResponseEntity<>(new ApiResponse(true,shareContactResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
