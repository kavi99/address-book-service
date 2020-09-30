package com.ggacademy.addressbook.service;

import com.ggacademy.addressbook.model.ContactRequest;
import com.ggacademy.addressbook.model.dto.ContactDto;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ContactService {

    public List<ContactDto> getContacts();

    ContactDto addContact(ContactRequest request);

    ContactDto updateContact(ContactRequest request,int id);

    ContactDto deleteContact(int id);

    String deletePhone(String phoneNumber);
}
