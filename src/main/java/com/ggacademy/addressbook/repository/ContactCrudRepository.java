package com.ggacademy.addressbook.repository;

import com.ggacademy.addressbook.model.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactCrudRepository extends CrudRepository<ContactEntity,Integer> {

}
