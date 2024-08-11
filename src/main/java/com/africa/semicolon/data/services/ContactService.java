package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;

public interface ContactService {
    AddContactsResponse addContact(AddContactRequest addContactRequest);
    RemoveContactResponse removeContact(RemoveContactRequest removeContactRequest);
    FindAllContactResponse findAllContacts(FindAllContactsRequest findAllContactsRequest);
    EditContactResponse editContact(EditContactRequest editContactRequest);
    ShareContactResponse shareContact(ShareContactRequest shareContactRequest);
    FindContactByPhoneNumberResponse findContactByPhoneNumber(FindContactByPhoneNumberRequest findContactByPhoneNumberRequest);
}
