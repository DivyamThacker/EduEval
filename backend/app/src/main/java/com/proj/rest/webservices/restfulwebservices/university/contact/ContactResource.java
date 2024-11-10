package com.proj.rest.webservices.restfulwebservices.university.contact;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.jpa.ContactDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.jpa.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.university.University;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/naac/university/{universityId}/contact-details")
public class ContactResource {
    private ContactDetailsRepository contactDetailsRepository;

    private UniversityRepository universityRepository;

	public ContactResource(UniversityRepository universityRepository, ContactDetailsRepository contactDetailsRepository) {
		this.universityRepository = universityRepository;
		this.contactDetailsRepository = contactDetailsRepository;
	}

    @PostMapping("")
	public ResponseEntity<List<Contact>> createContact(@PathVariable Integer universityId,@RequestBody List<Contact> contacts) {
		University university = universityRepository.findById(universityId).get();
		for (Contact c : contacts) {
			c.setUniversity(university);
			contactDetailsRepository.save(c);
		}
		List<Contact> savedContacts = universityRepository.findById(universityId).get().getContactDetails();
		return ResponseEntity.ok(savedContacts);
	}

	@PutMapping("/{contactId}")
	public ResponseEntity<Contact> updateContact(@PathVariable Integer universityId, @PathVariable Integer contactId, @Valid @RequestBody Contact contactDetails) {
		return contactDetailsRepository.findById(contactId).map(contact -> {
			contact.setName(contactDetails.getName());
			contact.setEmail(contactDetails.getEmail());
			contact.setPhone(contactDetails.getPhone());
			contact.setFax(contactDetails.getFax());
			contact.setDesignation(contactDetails.getDesignation());
			contact.setTelephone(contactDetails.getTelephone());
			contact.setUniversity(universityRepository.findById(universityId).get());
			Contact updatedContact = contactDetailsRepository.save(contact);
			return ResponseEntity.ok(updatedContact);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{contactId}")
	public ResponseEntity<Object> deleteContact(@PathVariable Integer universityId, @PathVariable Integer contactId) {
		return contactDetailsRepository.findById(contactId).map(contact -> {
			contactDetailsRepository.delete(contact);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("")
	public ResponseEntity<Object> deleteAllContacts(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<Contact> contacts = university.getContactDetails();
		for (Contact c : contacts) {
			System.out.println(c);
		}
		if (!contacts.isEmpty()) {
			contactDetailsRepository.deleteAll(contacts);
			university.getContactDetails().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("")
	public ResponseEntity<Object> getAllContacts(@PathVariable Integer universityId) {
		List<Contact> contacts = universityRepository.findById(universityId).get().getContactDetails();
		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getContact(@PathVariable Integer universityId, @PathVariable Integer contactId) {
		return contactDetailsRepository.findById(contactId).map(contact -> {
			return ResponseEntity.ok(contact);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
