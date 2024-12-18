package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.DistinguishedAcademician;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.DistinguishedAcademicianRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/academician")
public class DistinguishedAcademicianResource {

    private UniversityRepository universityRepository;
    private DistinguishedAcademicianRepository distinguishedAcademicianRepository;

    public DistinguishedAcademicianResource(UniversityRepository universityRepository, 
                                            DistinguishedAcademicianRepository distinguishedAcademicianRepository) {
        this.universityRepository = universityRepository;
        this.distinguishedAcademicianRepository = distinguishedAcademicianRepository;
    }

    @PostMapping("")
	public ResponseEntity<List<DistinguishedAcademician>> createFaculty(@PathVariable Integer universityId,@RequestBody List<DistinguishedAcademician> academicians) {
		University university = universityRepository.findById(universityId).get();
		for (DistinguishedAcademician f : academicians) {
			f.setUniversity(university);
			distinguishedAcademicianRepository.save(f);
		}
		List<DistinguishedAcademician> savedAcademicians = universityRepository.findById(universityId).get().getDistinguishedAcademicians();
		return ResponseEntity.ok(savedAcademicians);
	}

	@DeleteMapping("")
	public ResponseEntity<Object> deleteAllDistinguishedAcademicians(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<DistinguishedAcademician> distinguishedAcademicians = university.getDistinguishedAcademicians();
		for (DistinguishedAcademician c : distinguishedAcademicians) {
			System.out.println(c);
		}
		if (!distinguishedAcademicians.isEmpty()) {
			distinguishedAcademicianRepository.deleteAll(distinguishedAcademicians);
			university.getDistinguishedAcademicians().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
	}
}