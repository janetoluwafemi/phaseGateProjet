package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.exceptions.NumberAlreadyExistException;
import com.africa.semicolon.data.models.ContactRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ContactSeviceImplTest {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ContactService contactService;
    @BeforeEach
    public void setUp(){
        contactRepo.deleteAll();
    }
    @Test
    public void testThatContactCanAdd(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");
    }
   @Test
    public void testThatUserCanNotSaveTheSameNumber(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);

        AddContactRequest addContactRequest1 = new AddContactRequest();
        addContactRequest1.setFirstName("Janet");
        addContactRequest1.setLastName("Oluwafemi");
        addContactRequest1.setPhoneNumber("09058779721");
        assertThrows(NumberAlreadyExistException.class,()->  contactService.addContact(addContactRequest1));
    }

    @Test
    public void testThatContactCanRemove() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(contactRepo.count()).isEqualTo(1);

        RemoveContactRequest removeContactRequest = new RemoveContactRequest();
        removeContactRequest.setPhoneNumber("09058779721");
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");
        RemoveContactResponse removeContactResponse = contactService.removeContact(removeContactRequest);
        assertThat(contactRepo.count()).isEqualTo(0);

        assertThat(removeContactResponse.getMessage()).isEqualTo("Contact Removed Successfully");
    }
    @Test
    public void testThatContactsCanBeFound(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        addContactRequest.setFirstName("Love");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09066779701");
        contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        FindAllContactsRequest findAllContactsRequest = new FindAllContactsRequest();
        FindAllContactResponse findAllContactResponse = contactService.findAllContacts(findAllContactsRequest);
        assertThat(findAllContactResponse.getMessage()).isEqualTo("Contacts Found");
    }
    @Test
    public void testThatContactCanBeEdited(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");
        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setPhoneNumber("09058779721");
        editContactRequest.setFirstName("Kola");
        editContactRequest.setLastName("Olu");
        EditContactResponse editContactResponse = contactService.editContact(editContactRequest);
        assertThat(editContactResponse.getMessage()).isEqualTo("Contact Edited Successfully");
    }
    @Test
    public void testThatContactCanBeFoundByPhoneNumber(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        AddContactRequest addContactRequest1 = new AddContactRequest();
        addContactRequest1.setFirstName("Ola");
        addContactRequest1.setLastName("Oluwadarasimi");
        addContactRequest1.setPhoneNumber("080987876565");
        AddContactsResponse addContactsResponse1 = contactService.addContact(addContactRequest1);
        assertThat(addContactsResponse1.getMessage()).isEqualTo("Contact Added Successfully");

        FindContactByPhoneNumberRequest findContactByPhoneNumberRequest = new FindContactByPhoneNumberRequest();
        findContactByPhoneNumberRequest.setPhoneNumber("09058779721");
        FindContactByPhoneNumberResponse findContactByPhoneNumberResponse = contactService.findContactByPhoneNumber(findContactByPhoneNumberRequest);
        assertThat(findContactByPhoneNumberResponse.getMessage()).isEqualTo("Contact Found Successfully");
    }
    @Test
    public void testThatContactsCanBeShared(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Janet");
        addContactRequest.setLastName("Oluwafemi");
        addContactRequest.setPhoneNumber("09058779721");
        AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
        assertThat(addContactsResponse.getMessage()).isEqualTo("Contact Added Successfully");

        AddContactRequest addContactRequest1 = new AddContactRequest();
        addContactRequest1.setFirstName("Ifeoluwa");
        addContactRequest1.setLastName("Oluwafemi");
        addContactRequest1.setPhoneNumber("09012345678");
        AddContactsResponse addContactsResponse1 = contactService.addContact(addContactRequest1);
        assertThat(addContactsResponse1.getMessage()).isEqualTo("Contact Added Successfully");

        ShareContactRequest shareContactRequest = new ShareContactRequest();
        shareContactRequest.setFirstName("Ifeoluwa");
        shareContactRequest.setLastName("Oluwafemi");
        shareContactRequest.setPhoneNumber("09012345678");
        ShareContactResponse shareContactResponse = contactService.shareContact(shareContactRequest);
        assertThat(shareContactResponse.getMessage()).isEqualTo("Contact Shared Successfully");
    }
}
