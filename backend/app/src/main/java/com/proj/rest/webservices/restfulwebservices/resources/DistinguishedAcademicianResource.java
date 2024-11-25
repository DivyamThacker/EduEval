package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.DistinguishedAcademicianRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/distinguished-academician")
public class DistinguishedAcademicianResource {

    private UniversityRepository universityRepository;
    private DistinguishedAcademicianRepository distinguishedAcademicianRepository;

    public DistinguishedAcademicianResource(UniversityRepository universityRepository, 
                                            DistinguishedAcademicianRepository distinguishedAcademicianRepository) {
        this.universityRepository = universityRepository;
        this.distinguishedAcademicianRepository = distinguishedAcademicianRepository;
    }
}