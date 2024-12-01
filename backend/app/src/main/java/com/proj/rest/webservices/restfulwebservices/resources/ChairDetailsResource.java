package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.ChairDetails;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.ChairDetailsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/chair")
public class ChairDetailsResource {

    private UniversityRepository universityRepository;
    private ChairDetailsRepository chairDetailsRepository;

    public ChairDetailsResource(UniversityRepository universityRepository, 
                                ChairDetailsRepository chairDetailsRepository) {
        this.universityRepository = universityRepository;
        this.chairDetailsRepository = chairDetailsRepository;
    }

    @PostMapping("")
	public ResponseEntity<List<ChairDetails>> createChairDetails(@PathVariable Integer universityId,@RequestBody List<ChairDetails> chairs) {
		University university = universityRepository.findById(universityId).get();
		for (ChairDetails f : chairs) {
			f.setUniversity(university);
			chairDetailsRepository.save(f);
		}
		List<ChairDetails> savedChairs = universityRepository.findById(universityId).get().getChairDetails();
		return ResponseEntity.ok(savedChairs);
	}
}