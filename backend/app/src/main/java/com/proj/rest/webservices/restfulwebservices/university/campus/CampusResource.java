package com.proj.rest.webservices.restfulwebservices.university.campus;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proj.rest.webservices.restfulwebservices.jpa.CampusRepository;
import com.proj.rest.webservices.restfulwebservices.jpa.UniversityRepository;
import com.proj.rest.webservices.restfulwebservices.university.University;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/naac/university/{universityId}/campus")
public class CampusResource {

    private UniversityRepository universityRepository;

    private CampusRepository campusRepository;
	public CampusResource(UniversityRepository universityRepository, CampusRepository campusRepository) {
		this.universityRepository = universityRepository;
		this.campusRepository = campusRepository;
	}

    @PostMapping("")
	public ResponseEntity<Campus> createCampus(@PathVariable Integer universityId,@RequestBody Campus campus) {
		University university = universityRepository.findById(universityId).get();
		campus.setUniversity(university);
		Campus savedCampus = campusRepository.save(campus);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCampus.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{campusId}")
	public ResponseEntity<Campus> updateCampus(@PathVariable Integer universityId, @PathVariable Integer campusId, @Valid @RequestBody Campus campusDetails) {
		return campusRepository.findById(campusId).map(campus -> {
            campus.setAddress(campusDetails.getAddress());
            campus.setLocation(campusDetails.getLocation());
            campus.setCampusArea(campusDetails.getCampusArea());
            campus.setBuiltUpArea(campusDetails.getBuiltUpArea());
            campus.setProgrammesOffered(campusDetails.getProgrammesOffered());
            campus.setEstablishmentDate(campusDetails.getEstablishmentDate());
            campus.setRecognitionDate(campusDetails.getRecognitionDate());
            campus.setUniversity(universityRepository.findById(universityId).get());
            Campus updatedCampus = campusRepository.save(campus);
			return ResponseEntity.ok(updatedCampus);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{campusId}")
	public ResponseEntity<Object> deleteCampus(@PathVariable Integer universityId, @PathVariable Integer campusId) {
		return campusRepository.findById(campusId).map(campus -> {
			campusRepository.delete(campus);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("")
	public ResponseEntity<Object> getAllCampuss(@PathVariable Integer universityId) {
		List<Campus> campuses = universityRepository.findById(universityId).get().getCampuses();
		return ResponseEntity.ok(campuses);
	}

	@GetMapping("/{campusId}")
	public ResponseEntity<Campus> getCampus(@PathVariable Integer universityId, @PathVariable Integer campusId) {
		return campusRepository.findById(campusId).map(campus -> {
			return ResponseEntity.ok(campus);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
