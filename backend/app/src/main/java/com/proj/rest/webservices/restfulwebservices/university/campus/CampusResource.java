package com.proj.rest.webservices.restfulwebservices.university.campus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Campus>> createCampus(@PathVariable Integer universityId,@RequestBody List<Campus> campuses) {
		University university = universityRepository.findById(universityId).get();
		for (Campus c : campuses) {
			c.setUniversity(university);
			campusRepository.save(c);
		}
		List<Campus> savedCampuses = universityRepository.findById(universityId).get().getCampuses();
		return ResponseEntity.ok(savedCampuses);
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

	@DeleteMapping("")
	public ResponseEntity<Object> deleteAllCampuses(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<Campus> campuses = university.getCampuses();
		for (Campus c : campuses) {
			System.out.println(c);
		}
		if (!campuses.isEmpty()) {
			campusRepository.deleteAll(campuses);
			university.getCampuses().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
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
