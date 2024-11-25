package com.proj.rest.webservices.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proj.rest.webservices.restfulwebservices.models.EnrollmentDetails;

@Repository
public interface EnrollmentDetailsRepository extends JpaRepository<EnrollmentDetails, Integer> {}