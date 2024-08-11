package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.exceptions.IncorrectPasswordOrEmailException;
import com.africa.semicolon.data.exceptions.UserIsAlreadyRegisterException;
import com.africa.semicolon.data.exceptions.UserNotLoggedInException;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.models.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContactService contactService;
    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {

        boolean isRegistered = userRepo.existsByEmail(userRegisterRequest.getEmail());

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        if(!isRegistered){
            User user = new User();
            user.setFirstName(userRegisterRequest.getFirstName());
            user.setLastName(userRegisterRequest.getLastName());
            user.setEmail(userRegisterRequest.getEmail());
            user.setPassword(userRegisterRequest.getPassword());
            user.setLogIn(true);
            user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
            userRepo.save(user);
            userRegisterResponse.setMessage("User Registered Successfully");
            return userRegisterResponse;
        }
        throw new UserIsAlreadyRegisterException("User Have Already Registered ");
    }

    @Override
    public UserLogInResponse userLogIn(UserLogInRequest userLogInRequest) {
        User user = userRepo.findUserByEmail(userLogInRequest.getEmail());
        if(!userLogInRequest.getPassword().equals(user.getPassword()))
            throw new IncorrectPasswordOrEmailException("Email or Password incorrect");
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setMessage("Logged In Successfully");
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
        User user = userRepo.findUserByEmail(userCanShareContactRequest.getSenderEmail());
        if(user.isLogIn()){
            ShareContactRequest shareContactRequest = new ShareContactRequest();
            shareContactRequest.setPhoneNumber(userCanShareContactRequest.getPhoneNumber());
            ShareContactResponse response = contactService.shareContact(shareContactRequest);
            User user1 = userRepo.findUserByEmail(userCanShareContactRequest.getReceiverEmail());
            List <Contact>receiverUserContacts = user1.getContactList();
            Contact contact = new Contact();
            contact.setPhoneNumber(response.getPhoneNumber());
            contact.setFirstName(response.getFirstName());
            contact.setLastName(response.getLastName());
            contact.setId(response.getId());
            receiverUserContacts.add(contact);
            user1.setContactList(receiverUserContacts);
            userRepo.save(user1);
            return response;
        }
        throw new UserNotLoggedInException("Not Logged In");
    }

    @Override
    public AddContactsResponse userAddContact(AddContactRequest addContactRequest) {
        User user = userRepo.findUserByEmail(addContactRequest.getUserEmail());
        if(user.isLogIn()) {
            AddContactsResponse response = contactService.addContact(addContactRequest);
            Contact contact = new Contact();
            List<Contact>listOfContacts = user.getContactList();
            contact.setFirstName(response.getFirstName());
            contact.setLastName(response.getLastName());
            contact.setPhoneNumber(response.getPhoneNumber());
            listOfContacts.add(contact);
            user.setContactList(listOfContacts);
            userRepo.save(user);
            return response;
        }
        throw new UserNotLoggedInException("Not Logged In");
    }

    @Override
    public RemoveContactResponse userRemoveContact(UserRemoveContactRequest userRemoveContactRequest) {
        User user = userRepo.findUserByEmail(userRemoveContactRequest.getUserEmail());
        if(user.isLogIn()){
            RemoveContactResponse removeContactResponse = contactService.removeContact(userRemoveContactRequest.getPhoneNumber());
            return removeContactResponse;
        }
        throw new UserNotLoggedInException("Not Logged In");
    }
}
