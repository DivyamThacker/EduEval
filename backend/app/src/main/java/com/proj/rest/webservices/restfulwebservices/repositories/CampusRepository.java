package com.proj.rest.webservices.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.rest.webservices.restfulwebservices.models.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer> {

}