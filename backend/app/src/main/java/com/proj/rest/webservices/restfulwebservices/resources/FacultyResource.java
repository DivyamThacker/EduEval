package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.Faculty;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.FacultyRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/faculty")
public class FacultyResource {

    private UniversityRepository universityRepository;
    private FacultyRepository facultyRepository;

    public FacultyResource(UniversityRepository universityRepository, 
                           FacultyRepository facultyRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    @PostMapping("")
	public ResponseEntity<List<Faculty>> createFaculty(@PathVariable Integer universityId,@RequestBody List<Faculty> faculties) {
		University university = universityRepository.findById(universityId).get();
		for (Faculty f : faculties) {
			f.setUniversity(university);
			facultyRepository.save(f);
		}
		List<Faculty> savedFaculties = universityRepository.findById(universityId).get().getFaculties();
		return ResponseEntity.ok(savedFaculties);
	}
}