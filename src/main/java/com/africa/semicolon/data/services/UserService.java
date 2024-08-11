package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.dtos.requests.UserRemoveContactRequest;


public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    UserLogInResponse userLogIn(UserLogInRequest userLogInRequest);
    UserLogOutResponse userLogOut(UserLogOutRequest userLogOutRequest);
    ShareContactResponse userCanShareContact(UserCanShareContactRequest userCanShareContactRequest);
    AddContactsResponse userAddContact(AddContactRequest addBookRequest);
    RemoveContactResponse userRemoveContact(UserRemoveContactRequest userRemoveBookRequest);
}
