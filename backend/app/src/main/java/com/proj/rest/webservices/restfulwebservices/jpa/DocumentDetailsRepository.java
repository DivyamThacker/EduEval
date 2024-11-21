package com.proj.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.rest.webservices.restfulwebservices.university.document.DocumentDetails;

@Repository
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, Integer> {}