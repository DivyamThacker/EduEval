package com.proj.rest.webservices.restfulwebservices.staff;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proj.rest.webservices.restfulwebservices.jpa.StaffRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/naac/staff")
public class StaffResource {

    private StaffRepository staffRepository;

    public StaffResource(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping("")
    public ResponseEntity<Staff> createStaff(@Valid @RequestBody Staff staff, HttpServletRequest request) {
        Staff savedStaff = staffRepository.save(staff);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStaff.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("")
    public List<Staff> retrieveAllStaff() {
        return staffRepository.findAll();
    }

    @GetMapping("/{id}")
    public Staff retrieveStaff(@PathVariable Integer id) {
        return staffRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Integer id, @Valid @RequestBody Staff staffDetails) {
        return staffRepository.findById(id).map(staff -> {
			staff.setName(staffDetails.getName());
            staff.setDepartment(staffDetails.getDepartment());
            staff.setDesignation(staffDetails.getDesignation());
            staff.setEmail(staffDetails.getEmail());
            staff.setPhone(staffDetails.getPhone());
            staff.setQualification(staffDetails.getQualification());
            staff.setExperienceYears(staffDetails.getExperienceYears());
            staff.setIsTeaching(staffDetails.getIsTeaching());
            Staff updatedstaff = staffRepository.save(staff);
			return ResponseEntity.ok(updatedstaff);
		}).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Integer id) {
        staffRepository.deleteById(id);
    }
}
