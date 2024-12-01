package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.HrdcDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
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

    @PostMapping("")
    public ResponseEntity<HrdcDetails> createHrdcDetails(@PathVariable Integer universityId,@RequestBody HrdcDetails hrdcDetails) {
        University university = universityRepository.findById(universityId).get();
        hrdcDetails.setUniversity(university);
        HrdcDetails savedHrdcDetails = hrdcDetailsRepository.save(hrdcDetails);
        university.setHrdcDetails(savedHrdcDetails);
        universityRepository.save(university);
        return ResponseEntity.ok(savedHrdcDetails);
    }
}