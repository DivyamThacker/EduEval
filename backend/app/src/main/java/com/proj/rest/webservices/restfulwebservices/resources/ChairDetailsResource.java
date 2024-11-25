package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.ChairDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/chair")
public class ChairDetailsResource {

    private UniversityRepository universityRepository;
    private ChairDetailsRepository chairDetailsRepository;

    public ChairDetailsResource(UniversityRepository universityRepository, 
                                ChairDetailsRepository chairDetailsRepository) {
        this.universityRepository = universityRepository;
        this.chairDetailsRepository = chairDetailsRepository;
    }
}