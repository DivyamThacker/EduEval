package com.proj.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.rest.webservices.restfulwebservices.staff.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}

