package com.proj.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.rest.webservices.restfulwebservices.repositories.DepartmentEvaluationRepository;
import com.proj.rest.webservices.restfulwebservices.repositories.UniversityRepository;

@RestController
@RequestMapping("/api/naac/university/{universityId}/department-evaluation")
public class DepartmentEvaluationResource {

    private UniversityRepository universityRepository;
    private DepartmentEvaluationRepository departmentEvaluationRepository;

    public DepartmentEvaluationResource(UniversityRepository universityRepository, 
                                        DepartmentEvaluationRepository departmentEvaluationRepository) {
        this.universityRepository = universityRepository;
        this.departmentEvaluationRepository = departmentEvaluationRepository;
    }
}