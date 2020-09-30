package com.ggacademy.addressbook.repository;

import com.ggacademy.addressbook.model.ContactRequest;
import com.ggacademy.addressbook.model.entity.ContactEntity;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ContactRepository {

    List<ContactEntity> getContacts();

    ContactEntity addContact(ContactEntity entity);

    ContactEntity updateContact(ContactEntity entity, int id, ContactRequest request);

    ContactEntity deleteContact(int id);

    String deletePhone(String phone);
}
