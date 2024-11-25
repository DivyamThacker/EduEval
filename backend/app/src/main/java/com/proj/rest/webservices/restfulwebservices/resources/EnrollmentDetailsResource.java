package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.EnrollmentDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/enrollment")
public class EnrollmentDetailsResource {

    private UniversityRepository universityRepository;
    private EnrollmentDetailsRepository enrollmentDetailsRepository;

    public EnrollmentDetailsResource(UniversityRepository universityRepository, 
                                     EnrollmentDetailsRepository enrollmentDetailsRepository) {
        this.universityRepository = universityRepository;
        this.enrollmentDetailsRepository = enrollmentDetailsRepository;
    }
}