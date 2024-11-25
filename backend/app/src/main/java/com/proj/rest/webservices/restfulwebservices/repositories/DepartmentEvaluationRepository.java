package com.proj.rest.webservices.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proj.rest.webservices.restfulwebservices.models.DepartmentEvaluation;

@Repository
public interface DepartmentEvaluationRepository extends JpaRepository<DepartmentEvaluation, Integer> {}