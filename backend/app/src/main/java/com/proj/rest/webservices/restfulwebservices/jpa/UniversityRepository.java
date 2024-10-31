package com.proj.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.rest.webservices.restfulwebservices.university.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
