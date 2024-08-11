package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.exceptions.NumberAlreadyExistException;
import com.africa.semicolon.data.exceptions.NumberDoesNotExitException;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.ContactRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{
     @Autowired
    private ContactRepo contactRepo;
    @Override
    public AddContactsResponse addContact(AddContactRequest addContactRequest) {
        Contact contact = new Contact();
        contact.setFirstName(addContactRequest.getFirstName());
        contact.setLastName(addContactRequest.getLastName());
        contact.setPhoneNumber(addContactRequest.getPhoneNumber());
        boolean isContactExisting = contactRepo.existsByPhoneNumber(addContactRequest.getPhoneNumber());
        if(isContactExisting){
            throw new NumberAlreadyExistException("Number Already exits");
        }
        contactRepo.save(contact);
        AddContactsResponse addContactResponse = new AddContactsResponse();
        addContactResponse.setFirstName(contact.getFirstName());
        addContactResponse.setLastName(contact.getLastName());
        addContactResponse.setPhoneNumber(contact.getPhoneNumber());
        addContactResponse.setId(contact.getId());
        addContactResponse.setMessage("Contact Added Successfully");
        return addContactResponse;
    }

    @Override
    public RemoveContactResponse removeContact(RemoveContactRequest removeContactRequest) {
        Contact contact = contactRepo.findByPhoneNumber(removeContactRequest.getPhoneNumber());
        contactRepo.delete(contact);
        RemoveContactResponse removeContactResponse = new RemoveContactResponse();
        removeContactResponse.setMessage("Contact Removed Successfully");
        return removeContactResponse;
    }

    @Override
    public FindAllContactResponse findAllContacts(FindAllContactsRequest findAllContactsRequest) {
        List<Contact> contacts = contactRepo.findAll();
        FindAllContactResponse findAllContactResponse = new FindAllContactResponse();
        findAllContactResponse.setFirstName(contacts.getFirst().getFirstName());
        findAllContactResponse.setLastName(contacts.getLast().getLastName());
        findAllContactResponse.setPhoneNumber(contacts.getFirst().getPhoneNumber());
        findAllContactResponse.setMessage("Contacts Found");
        return findAllContactResponse;
    }

    @Override
    public EditContactResponse editContact(EditContactRequest editContactRequest) {
        for(Contact contact: contactRepo.findAll()){
            if(contact.getPhoneNumber().equalsIgnoreCase(editContactRequest.getPhoneNumber())){
                contact.setFirstName(editContactRequest.getFirstName());
                contact.setLastName(editContactRequest.getLastName());
                contactRepo.save(contact);
                EditContactResponse editContactResponse = new EditContactResponse();
                editContactResponse.setPhoneNumber(contact.getPhoneNumber());
                editContactResponse.setFirstName(contact.getFirstName());
                editContactResponse.setLastName(contact.getLastName());
                editContactResponse.setMessage("Contact Edited Successfully");
                return editContactResponse;
            }
        }
        throw new NumberDoesNotExitException("Number does not exit");
    }

    @Override
    public ShareContactResponse shareContact(ShareContactRequest shareContactRequest) {
        Contact contact = contactRepo.findByPhoneNumber(shareContactRequest.getPhoneNumber());
        ShareContactResponse shareContactResponse = new ShareContactResponse();
        shareContactResponse.setPhoneNumber(contact.getPhoneNumber());
        shareContactResponse.setFirstName(contact.getFirstName());
        shareContactResponse.setLastName(contact.getLastName());
        shareContactResponse.setId(contact.getId());
        shareContactResponse.setMessage("Contact Shared Successfully");
        return shareContactResponse;
    }

    @Override
    public FindContactByPhoneNumberResponse findContactByPhoneNumber(FindContactByPhoneNumberRequest findContactByPhoneNumberRequest) {
        Contact contact = contactRepo.findByPhoneNumber(findContactByPhoneNumberRequest.getPhoneNumber());
        FindContactByPhoneNumberResponse findContactByPhoneNumberResponse = new FindContactByPhoneNumberResponse();
        findContactByPhoneNumberResponse.setMessage("Contact Found Successfully");
        findContactByPhoneNumberResponse.setPhoneNumber(contact.getPhoneNumber());
        findContactByPhoneNumberResponse.setFirstName(contact.getFirstName());
        return findContactByPhoneNumberResponse;
    }
}
