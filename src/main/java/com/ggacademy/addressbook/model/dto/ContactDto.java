package com.ggacademy.addressbook.model.dto;

import java.util.List;
import java.util.Objects;

public class ContactDto {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private List<String> phones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactDto contactDto = (ContactDto) o;
        return Objects.equals(name, contactDto.name) &&
            Objects.equals(surname, contactDto.surname) &&
            Objects.equals(email, contactDto.email) &&
            Objects.equals(phones, contactDto.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, phones);
    }
}
