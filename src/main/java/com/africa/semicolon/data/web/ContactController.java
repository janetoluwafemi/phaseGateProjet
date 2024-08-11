package com.africa.semicolon.data.web;

import com.africa.semicolon.data.dtos.requests.*;
import com.africa.semicolon.data.dtos.responses.*;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.ContactRepo;
import com.africa.semicolon.data.services.ContactService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@Service
@RestController
@RequestMapping
public class ContactController {


    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping("create")
    public ResponseEntity<?> addContact(@RequestBody AddContactRequest addContactRequest) {
       try {
           AddContactsResponse addContactsResponse = contactService.addContact(addContactRequest);
           return new ResponseEntity<>(new ApiResponse(true,addContactsResponse), HttpStatus.CREATED);
       }
       catch(Exception exception){
           return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
       }
    }
    @DeleteMapping("delete/{phoneNumber}")
    public ResponseEntity<?> removeContact(@PathVariable("phoneNumber") RemoveContactRequest removeContactRequest){
        try {
            RemoveContactResponse removeContactResponse = contactService.removeContact(removeContactRequest);
            return new ResponseEntity<>(new ApiResponse(true,removeContactResponse), HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("find/{phoneNumber}")
    public ResponseEntity<?> findAllContacts(@PathVariable("phoneNumber") String phoneNumber){
        FindAllContactsRequest request = new FindAllContactsRequest();
        request.setPhoneNumber(phoneNumber);
        try {
            FindAllContactResponse findAllContactResponse = contactService.findAllContacts(request);
            return new ResponseEntity<>(new ApiResponse(true,findAllContactResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("edit/{phoneNumber}")
    public ResponseEntity<?> editContact(@PathVariable("phoneNumber") String phoneNumber){
        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setPhoneNumber(phoneNumber);
        try {
            EditContactResponse editContactResponse = contactService.editContact(editContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, editContactResponse),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("findContact/{phoneNumber}")
    public ResponseEntity<?> findContactByPhoneNumber(@PathVariable("phoneNumber")String phoneNumber){
        FindContactByPhoneNumberRequest findContactByPhoneNumberRequest = new FindContactByPhoneNumberRequest();
        findContactByPhoneNumberRequest.setPhoneNumber(phoneNumber);
        try {
            FindContactByPhoneNumberResponse findContactByPhoneNumber = contactService.findContactByPhoneNumber(findContactByPhoneNumberRequest);
            return new ResponseEntity<>(new ApiResponse(true,findContactByPhoneNumber),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
