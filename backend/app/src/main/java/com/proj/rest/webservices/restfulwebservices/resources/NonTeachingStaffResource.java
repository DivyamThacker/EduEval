package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.NonTeachingStaffRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/non-teaching-staff")
public class NonTeachingStaffResource {

    private UniversityRepository universityRepository;
    private NonTeachingStaffRepository nonTeachingStaffRepository;

    public NonTeachingStaffResource(UniversityRepository universityRepository, 
                                    NonTeachingStaffRepository nonTeachingStaffRepository) {
        this.universityRepository = universityRepository;
        this.nonTeachingStaffRepository = nonTeachingStaffRepository;
    }
}