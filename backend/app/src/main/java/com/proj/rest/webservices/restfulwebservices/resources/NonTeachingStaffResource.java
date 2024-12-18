package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.NonTeachingStaff;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.NonTeachingStaffRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/staff")
public class NonTeachingStaffResource {

    private UniversityRepository universityRepository;
    private NonTeachingStaffRepository nonTeachingStaffRepository;

    public NonTeachingStaffResource(UniversityRepository universityRepository, 
                                    NonTeachingStaffRepository nonTeachingStaffRepository) {
        this.universityRepository = universityRepository;
        this.nonTeachingStaffRepository = nonTeachingStaffRepository;
    }

    @PostMapping("")
	public ResponseEntity<List<NonTeachingStaff>> createNonTeachingStaff(@PathVariable Integer universityId,@RequestBody List<NonTeachingStaff> staffList) {
		University university = universityRepository.findById(universityId).get();
		for (NonTeachingStaff c : staffList) {
			c.setUniversity(university);
			nonTeachingStaffRepository.save(c);
		}
		List<NonTeachingStaff> savedNonTeachingStaff = universityRepository.findById(universityId).get().getNonTeachingStaff();
		return ResponseEntity.ok(savedNonTeachingStaff);
	}

	@DeleteMapping("")
	public ResponseEntity<Object> deleteAllNonTeachingStaff(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<NonTeachingStaff> staff = university.getNonTeachingStaff();
		for (NonTeachingStaff c : staff) {
			System.out.println(c);
		}
		if (!staff.isEmpty()) {
			nonTeachingStaffRepository.deleteAll(staff);
			university.getNonTeachingStaff().clear();       // Remove from university
			universityRepository.save(university);        // Persist update to university
		}
		return ResponseEntity.noContent().build();
	}
}