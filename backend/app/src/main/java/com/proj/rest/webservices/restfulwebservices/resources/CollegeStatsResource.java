package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.models.CollegeStats;
import com.proj.rest.webservices.restfulwebservices.models.University;
import com.proj.rest.webservices.restfulwebservices.repositories.CollegeStatsRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/naac/university/{universityId}/college-info")
public class CollegeStatsResource {
    
     private UniversityRepository universityRepository;
     private CollegeStatsRepository collegeStatsRepository;

    public CollegeStatsResource(UniversityRepository universityRepository, CollegeStatsRepository collegeStatsRepository) {
        this.universityRepository = universityRepository;
        this.collegeStatsRepository = collegeStatsRepository;
    }

    @PostMapping("")
    public ResponseEntity<CollegeStats> createCollegeStats(@PathVariable Integer universityId,@RequestBody CollegeStats collegeStats) {
        University university = universityRepository.findById(universityId).get();
        collegeStats.setUniversity(university);
        CollegeStats savedCollegeStats = collegeStatsRepository.save(collegeStats);
        university.setCollegeStats(savedCollegeStats);
        universityRepository.save(university);
        return ResponseEntity.ok(savedCollegeStats);
    }

    @PutMapping("/{collegeInfoId}")
    public ResponseEntity<CollegeStats> updateCollegeStats(@PathVariable String universityId,
     @PathVariable Integer collegeInfoId,@RequestBody CollegeStats collegeStats) {
        return universityRepository.findById(Integer.parseInt(universityId)).map(university -> {
            CollegeStats collegeStatsToUpdate = collegeStatsRepository.findById(collegeInfoId).orElseThrow(() -> new RuntimeException("CollegeStats not found"));
            collegeStatsToUpdate.setAffiliatedColleges(collegeStats.getAffiliatedColleges());
            collegeStatsToUpdate.setAutonomousColleges(collegeStats.getAutonomousColleges());
            collegeStatsToUpdate.setCollegesUnder2f(collegeStats.getCollegesUnder2f());
            collegeStatsToUpdate.setCollegesUnder2f12b(collegeStats.getCollegesUnder2f12b());
            collegeStatsToUpdate.setCollegesWithExcellence(collegeStats.getCollegesWithExcellence());
            collegeStatsToUpdate.setCollegesWithPgDepartments(collegeStats.getCollegesWithPgDepartments());
            collegeStatsToUpdate.setCollegesWithResearchDepartments(collegeStats.getCollegesWithResearchDepartments());
            collegeStatsToUpdate.setConstituentColleges(collegeStats.getConstituentColleges());
            collegeStatsToUpdate.setNaacAccredited(collegeStats.getNaacAccredited());
            collegeStatsToUpdate.setResearchInstitutes(collegeStats.getResearchInstitutes());
            CollegeStats updatedCollegeStats = collegeStatsRepository.save(collegeStatsToUpdate);
            return ResponseEntity.ok(updatedCollegeStats);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<CollegeStats> getCollegeStats(@PathVariable Integer universityId) {
        University university = universityRepository.findById(universityId).get();
        CollegeStats collegeStats = university.getCollegeStats();
        return ResponseEntity.ok(collegeStats);
    }
}
