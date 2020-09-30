package com.ggacademy.addressbook.controller;

import com.ggacademy.addressbook.model.ContactRequest;
import com.ggacademy.addressbook.model.dto.ContactDto;
import com.ggacademy.addressbook.service.ContactService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/api/v1/address-book/contacts")
    public List<ContactDto> getContacts() {
        return contactService.getContacts();
    }
    @PostMapping(value = "/api/v1/address-book/contacts")
    public ContactDto getContacts(@RequestBody ContactRequest request) {
        return contactService.addContact(request);
    }
    @PutMapping(value = "/api/v1/address-book/contacts")
    public ContactDto updateContacts(@RequestBody ContactRequest request, @RequestParam int id) {
        return contactService.updateContact(request, id);
    }
    @DeleteMapping(value = "/api/v1/address-book/contacts")
    public ContactDto deleteContacts(@RequestParam int id) {
        return contactService.deleteContact(id);
    }
    @DeleteMapping(value = "/api/v1/address-book/phones")
    public String deletePhone(@RequestParam String phoneNumber) {
        return contactService.deletePhone(phoneNumber);
    }
}