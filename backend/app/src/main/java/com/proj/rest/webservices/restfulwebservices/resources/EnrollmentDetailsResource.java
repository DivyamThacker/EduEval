package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestBody Boolean hasIntegrated, @RequestBody (required = false) Integer totalIntegratedPrograms,
    @RequestBody (required = false) List<EnrollmentDetails> enrollments) {
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