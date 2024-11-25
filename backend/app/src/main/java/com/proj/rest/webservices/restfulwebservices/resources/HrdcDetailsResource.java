package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.HrdcDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/hrdc")
public class HrdcDetailsResource {

    private UniversityRepository universityRepository;
    private HrdcDetailsRepository hrdcDetailsRepository;

    public HrdcDetailsResource(UniversityRepository universityRepository, 
                               HrdcDetailsRepository hrdcDetailsRepository) {
        this.universityRepository = universityRepository;
        this.hrdcDetailsRepository = hrdcDetailsRepository;
    }
}