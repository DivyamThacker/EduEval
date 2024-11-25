package com.proj.rest.webservices.restfulwebservices.resources;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proj.rest.webservices.restfulwebservices.repositories.ContactDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.models.Contact;
import com.proj.rest.webservices.restfulwebservices.models.University;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/naac/university")
public class UniversityResource {

    private UniversityRepository universityRepository;
	
	private ContactDetailsRepository contactDetailsRepository;

	public UniversityResource(UniversityRepository universityRepository, ContactDetailsRepository contactDetailsRepository) {
		this.universityRepository = universityRepository;
		this.contactDetailsRepository = contactDetailsRepository;
	}
    
	@PostMapping("/basic-info")
	public ResponseEntity<University> createUniversity(@Valid @RequestBody University university, HttpServletRequest request) {
		System.out.println(university);
		University savedUniversity = universityRepository.save(university);
		List<Contact> contactDetails = university.getContactDetails();
		if (contactDetails != null) {
			for (Contact contact : contactDetails) {
				contact.setUniversity(savedUniversity);
				contactDetailsRepository.save(contact);
			}
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUniversity.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedUniversity);
	}
	
	@GetMapping("")
	public List<University> retrieveAllUniversities() {
		return universityRepository.findAll();
	}

	@GetMapping("/{universityId}")
	public University retrieveUniversity(@PathVariable Integer universityId) {	
		return universityRepository.findById(universityId).get();
	}

	@PutMapping("/{universityId}/basic-info")
	public ResponseEntity<University> updateUniversity(@PathVariable Integer universityId, @Valid @RequestBody University universityDetails) {
		return universityRepository.findById(universityId).map(university -> {
			university.setName(universityDetails.getName());
			university.setAddress(universityDetails.getAddress());
			university.setCity(universityDetails.getCity());
			university.setState(universityDetails.getState());
			university.setPincode(universityDetails.getPincode());
			university.setWebsiteUrl(universityDetails.getWebsiteUrl());
			university.setNature(universityDetails.getNature());
			university.setType(universityDetails.getType());
			university.setEstablishmentDate(universityDetails.getEstablishmentDate());
			university.setPriorStatus(universityDetails.getPriorStatus());
			University updatedUniversity = universityRepository.save(university);
			return ResponseEntity.ok(updatedUniversity);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
