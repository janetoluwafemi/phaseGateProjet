package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.exceptions.IncompleteInformations;
import com.africa.semicolon.data.exceptions.IncorrectPasswordOrEmailException;
import com.africa.semicolon.data.exceptions.UserIsAlreadyRegisterException;
import com.africa.semicolon.data.exceptions.UserNotLoggedInException;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.models.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContactService contactService;
    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        boolean isRegistered = userRepo.existsByEmail(userRegisterRequest.getEmail());
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        if (isRegistered) {
            throw new UserIsAlreadyRegisterException("Email already Exist");
        }
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(userRegisterRequest.getPassword());
        user.setLogIn(true);
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        if (user.getPhoneNumber().isEmpty() || user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getPassword().isEmpty()) {
            throw new IncompleteInformations("Information Not Complete");
        }
        if (user.getPhoneNumber().length() == 11) {
            userRepo.save(user);
            userRegisterResponse.setId(user.getId());
            userRegisterResponse.setMessage("User Registered Successfully");
            return userRegisterResponse;
        }
        throw new RuntimeException("phoneNumber is not valid");
    }

    @Override
    public UserLogInResponse userLogIn(UserLogInRequest userLogInRequest) {
        User user = userRepo.findUserByEmail(userLogInRequest.getEmail());
        if(!userLogInRequest.getPassword().equals(user.getPassword()))
            throw new IncorrectPasswordOrEmailException("Email or Password incorrect");
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setMessage("Logged In Successfully");
        userLogInResponse.setId(user.getId());
        userRepo.save(user);
        return userLogInResponse;
    }

    @Override
    public UserLogOutResponse userLogOut(UserLogOutRequest userLogOutRequest) {
        User user = userRepo.findUserByEmail(userLogOutRequest.getEmail());
        user.setLogIn(false);
        UserLogOutResponse userLogOutResponse = new UserLogOutResponse();
        userLogOutResponse.setMessage("Logged Out Successfully");
        userRepo.save(user);
        return userLogOutResponse;
    }

    @Override
    public ShareContactResponse userCanShareContact(UserCanShareContactRequest userCanShareContactRequest) {
        User user = userRepo.findUserById(userCanShareContactRequest.getId())
                .orElseThrow(() -> new UserNotLoggedInException("User Not Found"));
        if(user.isLogIn()){
            ShareContactRequest shareContactRequest = new ShareContactRequest();
            shareContactRequest.setPhoneNumber(userCanShareContactRequest.getPhoneNumber());
            ShareContactResponse response = contactService.shareContact(shareContactRequest);
            User user1 = userRepo.findUserByEmail(userCanShareContactRequest.getReceiverEmail());
            response.setId(user1.getId());
            response.setPhoneNumber(user1.getPhoneNumber());
            userRepo.save(user1);
            return response;
        }
        throw new UserNotLoggedInException("Not Logged In");
    }

    @Override
    public AddContactsResponse userAddContact(AddContactRequest addContactRequest) {
        User user = userRepo.findUserById(addContactRequest.getId())
                .orElseThrow(()->new UserNotLoggedInException("User does not exit"));
        if(user.isLogIn()) {
            AddContactsResponse response = contactService.addContact(addContactRequest);
            userRepo.save(user);
            return response;
        }
        throw new UserNotLoggedInException("Not Logged In");

    }

    @Override
    public RemoveContactResponse userRemoveContact(RemoveContactRequest removeContactRequest) {
        User user = userRepo.findUserById(removeContactRequest.getId())
                .orElseThrow(()->new UserNotLoggedInException("User does not exit"));        if(user.isLogIn()){
            return contactService.removeContact(removeContactRequest);
        }
        throw new UserNotLoggedInException("Not Logged In");
    }
}
