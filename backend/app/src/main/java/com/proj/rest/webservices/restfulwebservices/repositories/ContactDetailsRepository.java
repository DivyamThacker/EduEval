package com.proj.rest.webservices.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.rest.webservices.restfulwebservices.models.Contact;

@Repository
public interface ContactDetailsRepository extends JpaRepository<Contact, Integer> {

}
