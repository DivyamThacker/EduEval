package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.FacultyRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/faculty")
public class FacultyResource {

    private UniversityRepository universityRepository;
    private FacultyRepository facultyRepository;

    public FacultyResource(UniversityRepository universityRepository, 
                           FacultyRepository facultyRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }
}