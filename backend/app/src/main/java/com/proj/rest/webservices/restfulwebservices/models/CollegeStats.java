package com.proj.rest.webservices.restfulwebservices.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity(name = "college_stats")
@Data
@NoArgsConstructor
public class CollegeStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer constituentColleges;
    private Integer affiliatedColleges;
    private Integer collegesUnder2f;
    private Integer collegesUnder2f12b;
    private Integer naacAccredited;
    private Integer collegesWithExcellence;
    private Integer autonomousColleges;
    private Integer collegesWithPgDepartments;
    private Integer collegesWithResearchDepartments;
    private Integer researchInstitutes;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private University university;
}
