package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.rest.webservices.restfulwebservices.models.EnrollmentDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
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

     @PostMapping("")
	public ResponseEntity<List<EnrollmentDetails>> createEnrollmentDetails(@PathVariable Integer universityId,
    @RequestParam("hasIntegrated") String hasIntegratedJson, @RequestParam(required = false, name = "totalIntegratedPrograms") String totalIntegratedProgramsJson,
    @RequestPart(required = false, name="enrollments") String enrollmentsJson) throws JsonProcessingException {

    // Deserialize JSON 
    ObjectMapper objectMapper = new ObjectMapper();
    List<EnrollmentDetails> enrollments = objectMapper.readValue(
        enrollmentsJson, 
        new TypeReference<List<EnrollmentDetails>>() {}
    );

    Boolean hasIntegrated = objectMapper.readValue(hasIntegratedJson, Boolean.class);
    Integer totalIntegratedPrograms = objectMapper.readValue(totalIntegratedProgramsJson, Integer.class);

		University university = universityRepository.findById(universityId).get();
        university.setHasIntegratedPrograms(hasIntegrated);
        university.setTotalIntegratedPrograms(totalIntegratedPrograms);
        universityRepository.save(university);

		for (EnrollmentDetails c : enrollments) {
			c.setUniversity(university);
			enrollmentDetailsRepository.save(c);
		}
		List<EnrollmentDetails> savedEnrollmentDetails = universityRepository.findById(universityId).get().getEnrollmentDetails();
		return ResponseEntity.ok(savedEnrollmentDetails);
	}


}