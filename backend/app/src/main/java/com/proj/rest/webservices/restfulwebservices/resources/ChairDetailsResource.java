package com.proj.rest.webservices.restfulwebservices.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@DeleteMapping("")
	public ResponseEntity<Object> deleteAllChairs(@PathVariable Integer universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university == null) {
			return ResponseEntity.notFound().build();
		}
		List<ChairDetails> chairs = university.getChairDetails();
		for (ChairDetails c : chairs) {
			System.out.println(c);
		}
		if (!chairs.isEmpty()) {
			chairDetailsRepository.deleteAll(chairs);
			university.getChairDetails().clear();       
			universityRepository.save(university);       
		}
		return ResponseEntity.noContent().build();
	}
}