package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.AccreditationDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/accreditation")
public class AccreditationDetailsResource {

    private UniversityRepository universityRepository;

    private AccreditationDetailsRepository accreditationDetailsRepository;
	public AccreditationDetailsResource(UniversityRepository universityRepository, 
                        AccreditationDetailsRepository accreditationDetailsRepository) {
		this.universityRepository = universityRepository;
        this.accreditationDetailsRepository = accreditationDetailsRepository;
	}

}