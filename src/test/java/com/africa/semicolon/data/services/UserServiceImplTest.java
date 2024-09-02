package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.exceptions.UserIsAlreadyRegisterException;
import com.africa.semicolon.data.models.ContactRepo;
import com.africa.semicolon.data.models.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp(){
        contactRepo.deleteAll();
        userRepo.deleteAll();
    }
    @Test
    public void testThatUserCanRegister(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Janet");
        userRegisterRequest.setLastName("Femi2");
        userRegisterRequest.setPhoneNumber("09087654321");
        userRegisterRequest.setEmail("Femi2");
        userRegisterRequest.setPassword("54");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");
    }
    @Test
    public void testThatUserCanNotRegisterTwice(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Janet");
        userRegisterRequest.setLastName("Oluwafemi");
        userRegisterRequest.setPhoneNumber("09087654321");
        userRegisterRequest.setEmail("5tt");
        userRegisterRequest.setPassword("54");
        userService.registerUser(userRegisterRequest);
        assertEquals(1,userRepo.count());

        userRegisterRequest.setFirstName("Janet");
        userRegisterRequest.setLastName("Oluwafemi");
        userRegisterRequest.setPhoneNumber("09087654321");
        userRegisterRequest.setEmail("5tt");
        userRegisterRequest.setPassword("54");

        assertThrows(UserIsAlreadyRegisterException.class, ()-> userService.registerUser(userRegisterRequest));
    }
    @Test
    public void testThatUserCanLogIn(){
         UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("OLa");
        userRegisterRequest.setLastName("Femi");
        userRegisterRequest.setPhoneNumber("09087654456");
        userRegisterRequest.setEmail("Ola@email.com");
        userRegisterRequest.setPassword("1234");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");

        UserLogInRequest userLogInRequest = new UserLogInRequest();
        userLogInRequest.setEmail("Ola@email.com");
        userLogInRequest.setPassword("1234");
        UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
        assertThat(userLogInResponse.getMessage()).isEqualTo("Logged In Successfully");
    }
    @Test
    public void testThatUserCanLogOut(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("OLa");
        userRegisterRequest.setLastName("Femi");
        userRegisterRequest.setPhoneNumber("09087654456");
        userRegisterRequest.setEmail("Ola@email.com");
        userRegisterRequest.setPassword("1234");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");

        UserLogInRequest userLogInRequest = new UserLogInRequest();
        userLogInRequest.setEmail("Ola@email.com");
        userLogInRequest.setPassword("1234");
        UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
        assertThat(userLogInResponse.getMessage()).isEqualTo("Logged In Successfully");

        UserLogOutRequest userLogOutRequest = new UserLogOutRequest();
        userLogOutRequest.setEmail("Ola@email.com");
        UserLogOutResponse userLogOutResponse = userService.userLogOut(userLogOutRequest);
        assertThat(userLogOutResponse.getMessage()).isEqualTo("Logged Out Successfully");
    }
    @Test
    public void testThatContactCanBeAdded(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("OLa");
        userRegisterRequest.setLastName("Femi");
        userRegisterRequest.setPhoneNumber("09087654456");
        userRegisterRequest.setEmail("Ola@email.com");
        userRegisterRequest.setPassword("1234");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");

        UserLogInRequest userLogInRequest = new UserLogInRequest();
        userLogInRequest.setEmail("Ola@email.com");
        userLogInRequest.setPassword("1234");
        UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
        assertThat(userLogInResponse.getMessage()).isEqualTo("Logged In Successfully");

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Lovee");
        addContactRequest.setLastName("Sam");
        addContactRequest.setPhoneNumber("090109283745");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");
    }
    @Test
    public void testThatContactCanBeRemoved(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("OLa");
        userRegisterRequest.setLastName("Femi");
        userRegisterRequest.setPhoneNumber("09087654456");
        userRegisterRequest.setEmail("Ola@email.com");
        userRegisterRequest.setPassword("1234");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");

        UserLogInRequest userLogInRequest = new UserLogInRequest();
        userLogInRequest.setEmail("Ola@email.com");
        userLogInRequest.setPassword("1234");
        UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
        assertThat(userLogInResponse.getMessage()).isEqualTo("Logged In Successfully");

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Lovee");
        addContactRequest.setLastName("Sam");
        addContactRequest.setPhoneNumber("090109283745");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        RemoveContactRequest removeContactRequest = new RemoveContactRequest();
        removeContactRequest.setPhoneNumber("090109283745");
        removeContactRequest.setFirstName("Lovee");
        removeContactRequest.setLastName("Sam");
        RemoveContactResponse removeContactResponse = contactService.removeContact(removeContactRequest);
        assertThat(removeContactResponse.getMessage()).isEqualTo("Contact Removed Successfully");
    }
    @Test
    public void testThatContactCanBeShared(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("OLa");
        userRegisterRequest.setLastName("Femi");
        userRegisterRequest.setPhoneNumber("09087654456");
        userRegisterRequest.setEmail("Ola@email.com");
        userRegisterRequest.setPassword("123");
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User Registered Successfully");

        UserRegisterRequest userRegisterRequest1 = new UserRegisterRequest();
        userRegisterRequest1.setFirstName("OLa2");
        userRegisterRequest1.setLastName("Femi2");
        userRegisterRequest1.setPhoneNumber("09087654485");
        userRegisterRequest1.setEmail("Femi27@email.com");
        userRegisterRequest1.setPassword("1234");
        UserRegisterResponse userRegisterResponse1 = userService.registerUser(userRegisterRequest1);
        assertThat(userRegisterResponse1.getMessage()).isEqualTo("User Registered Successfully");


        UserLogInRequest userLogInRequest = new UserLogInRequest();
        userLogInRequest.setEmail("Ola@email.com");
        userLogInRequest.setPassword("123");
        UserLogInResponse userLogInResponse = userService.userLogIn(userLogInRequest);
        assertThat(userLogInResponse.getMessage()).isEqualTo("Logged In Successfully");

        UserLogInRequest userLogInRequest1 = new UserLogInRequest();
        userLogInRequest1.setEmail("Femi27@email.com");
        userLogInRequest1.setPassword("1234");
        UserLogInResponse userLogInResponse1 = userService.userLogIn(userLogInRequest1);
        assertThat(userLogInResponse1.getMessage()).isEqualTo("Logged In Successfully");

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("OLa2");
        addContactRequest.setLastName("Femi2");
        addContactRequest.setPhoneNumber("090875434466");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        UserCanShareContactRequest userCanShareContactRequest = new UserCanShareContactRequest();
        userCanShareContactRequest.setReceiverEmail("Femi27@email.com");
        userCanShareContactRequest.setSenderEmail("Ola@email.com");
        userCanShareContactRequest.setPhoneNumber("090875434466");
        ShareContactResponse userCanShareContactResponse = userService.userCanShareContact(userCanShareContactRequest);
        assertThat(userCanShareContactResponse.getMessage()).isEqualTo("Contact Shared Successfully");
    }
}
