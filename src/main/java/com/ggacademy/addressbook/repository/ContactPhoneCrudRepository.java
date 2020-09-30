package com.ggacademy.addressbook.repository;

import com.ggacademy.addressbook.model.entity.ContactPhoneEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactPhoneCrudRepository extends CrudRepository<ContactPhoneEntity, Integer> {

}
