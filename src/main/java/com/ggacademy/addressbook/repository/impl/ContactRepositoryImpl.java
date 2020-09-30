package com.ggacademy.addressbook.repository.impl;

import com.ggacademy.addressbook.model.ContactRequest;
import com.ggacademy.addressbook.model.entity.ContactEntity;
import com.ggacademy.addressbook.model.entity.ContactPhoneEntity;
import com.ggacademy.addressbook.repository.ContactCrudRepository;
import com.ggacademy.addressbook.repository.ContactPhoneCrudRepository;
import com.ggacademy.addressbook.repository.ContactRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

@Service
public class ContactRepositoryImpl implements ContactRepository {

    private final ContactCrudRepository contactCrudRepository;
    private final ContactPhoneCrudRepository ContactPhoneCrudRepository;

    public ContactRepositoryImpl(ContactCrudRepository contactCrudRepository, com.ggacademy.addressbook.repository.ContactPhoneCrudRepository contactPhoneCrudRepository) {
        this.contactCrudRepository = contactCrudRepository;
        ContactPhoneCrudRepository = contactPhoneCrudRepository;
    }

    @Override
    public List<ContactEntity> getContacts() {
        List<ContactEntity> entities = new ArrayList<>();
        Iterable<ContactEntity> contactEntityIterable = contactCrudRepository.findAll();
        contactEntityIterable.forEach(entities::add);
        return entities;
    }

    @Override
    public ContactEntity addContact(ContactEntity entity) {
        return contactCrudRepository.save(entity);
    }

    @Override
    public ContactEntity deleteContact(int id) {
        ContactEntity updateContact = contactCrudRepository.findById(id).get();
        contactCrudRepository.deleteById(id);
        return updateContact;
    }

    @Override
    public String deletePhone(String phone) {
        Iterable<ContactPhoneEntity> contactPhoneEntityIterable = ContactPhoneCrudRepository.findAll();
        contactPhoneEntityIterable
                .forEach(p -> {
                    if (p.getPhoneNumber().equals(phone) ){
                        ContactPhoneCrudRepository.deleteById(p.getPhoneId());
                    }
                });
        return phone;
    }

    @Override
    public ContactEntity updateContact(ContactEntity entity, int id, ContactRequest request) {
        ContactEntity updateContact = contactCrudRepository.findById(id).get();
        Iterable<ContactPhoneEntity> contactPhoneEntityIterable = ContactPhoneCrudRepository.findAll();
        contactPhoneEntityIterable
                .forEach(p -> {
                    if (p.getContacts().getId() == id && p.getContacts().getId() != null){
                        ContactPhoneCrudRepository.deleteById(p.getPhoneId());
                    }
                });
        request.getPhones()
                .forEach(p -> {
                    ContactPhoneEntity contactPhoneEntity = new ContactPhoneEntity();
                    contactPhoneEntity.setContacts(updateContact);
                    contactPhoneEntity.setPhoneNumber(p);
                    ContactPhoneCrudRepository.save(contactPhoneEntity);
                    updateContact.addPhone(contactPhoneEntity);
                });
        updateContact.setName(entity.getName());
        updateContact.setSurname(entity.getSurname());
        updateContact.setEmail(entity.getEmail());
        contactCrudRepository.save(updateContact);
        return updateContact;
    }
}
