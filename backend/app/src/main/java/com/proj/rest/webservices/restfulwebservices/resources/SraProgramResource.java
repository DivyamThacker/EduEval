package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.SraProgramRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/sra-program")
public class SraProgramResource {

    private UniversityRepository universityRepository;
    private SraProgramRepository sraProgramRepository;

    public SraProgramResource(UniversityRepository universityRepository, 
                              SraProgramRepository sraProgramRepository) {
        this.universityRepository = universityRepository;
        this.sraProgramRepository = sraProgramRepository;
    }
}