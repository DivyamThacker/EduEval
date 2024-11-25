package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.ElectoralLiteracyDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/electoral-literacy")
public class ElectoralLiteracyDetailsResource {

    private UniversityRepository universityRepository;
    private ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository;

    public ElectoralLiteracyDetailsResource(UniversityRepository universityRepository, 
                                            ElectoralLiteracyDetailsRepository electoralLiteracyDetailsRepository) {
        this.universityRepository = universityRepository;
        this.electoralLiteracyDetailsRepository = electoralLiteracyDetailsRepository;
    }
}