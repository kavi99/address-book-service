package com.ggacademy.addressbook.service.impl;

import com.ggacademy.addressbook.model.ContactRequest;
import com.ggacademy.addressbook.model.dto.ContactDto;
import com.ggacademy.addressbook.model.entity.ContactEntity;
import com.ggacademy.addressbook.model.entity.ContactPhoneEntity;
import com.ggacademy.addressbook.repository.ContactPhoneCrudRepository;
import com.ggacademy.addressbook.repository.ContactRepository;
import com.ggacademy.addressbook.service.ContactService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactPhoneCrudRepository contactPhoneCrudRepository;

    public ContactServiceImpl(ContactRepository contactRepository,
        ContactPhoneCrudRepository contactPhoneCrudRepository) {
        this.contactRepository = contactRepository;
        this.contactPhoneCrudRepository = contactPhoneCrudRepository;
    }

    @Override
    public List<ContactDto> getContacts() {
        return contactRepository.getContacts()
            .stream()
            .map(s -> convert(s))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public ContactDto addContact(ContactRequest request) {
        ContactEntity entity = convert(request);
        ContactEntity contactEntity = contactRepository.addContact(entity);
        request.getPhones()
            .forEach(p -> {
            ContactPhoneEntity contactPhoneEntity = new ContactPhoneEntity();
            contactPhoneEntity.setContacts(contactEntity);
            contactPhoneEntity.setPhoneNumber(p);
            contactPhoneCrudRepository.save(contactPhoneEntity);
            contactEntity.addPhone(contactPhoneEntity);
        });
        ContactDto contactDto = convert(contactEntity);
        return contactDto;
    }

    @Override
    public ContactDto updateContact(ContactRequest request, int id) {
        ContactEntity entity = convert(request);
        ContactEntity contactEntity = contactRepository.updateContact(entity, id, request);
        ContactDto contactDto = convert(contactEntity);
        return contactDto;
    }

    @Override
    public ContactDto deleteContact(int id) {
        ContactEntity contactEntity = contactRepository.deleteContact(id);
        ContactDto contactDto = convert(contactEntity);
        return contactDto;
    }

    @Override
    public String deletePhone(String phoneNumber) {
        String phone = contactRepository.deletePhone(phoneNumber);
        return phone;
    }


    public ContactEntity convert(ContactRequest request) {
        ContactEntity contactDto = new ContactEntity();
        contactDto.setEmail(request.getEmail());
        contactDto.setName(request.getName());
        contactDto.setSurname(request.getSurname());
        return contactDto;
    }

    public ContactDto convert(ContactEntity entity) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(entity.getId());
        contactDto.setEmail(entity.getEmail());
        contactDto.setName(entity.getName());
        contactDto.setSurname(entity.getSurname());
        contactDto.setPhones(entity.getPhones().stream().map(ContactPhoneEntity::getPhoneNumber).collect(Collectors.toList()));
        return contactDto;
    }
}
