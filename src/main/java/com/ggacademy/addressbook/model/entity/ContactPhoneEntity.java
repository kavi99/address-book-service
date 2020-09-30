package com.ggacademy.addressbook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_phone",catalog = "address_book")
public class ContactPhoneEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id",nullable = false)
    private ContactEntity contacts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer phoneId;
    @Column(name = "phone_number")
    private String phoneNumber;

    public ContactEntity getContacts() {
        return contacts;
    }

    public void setContacts(ContactEntity contacts) {
        this.contacts = contacts;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
