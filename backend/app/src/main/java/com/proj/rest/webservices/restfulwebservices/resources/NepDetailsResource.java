package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.NepDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/nep")
public class NepDetailsResource {

    private UniversityRepository universityRepository;
    private NepDetailsRepository nepDetailsRepository;

    public NepDetailsResource(UniversityRepository universityRepository, 
                              NepDetailsRepository nepDetailsRepository) {
        this.universityRepository = universityRepository;
        this.nepDetailsRepository = nepDetailsRepository;
    }
}