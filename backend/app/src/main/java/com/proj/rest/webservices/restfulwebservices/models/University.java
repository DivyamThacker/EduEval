package com.proj.rest.webservices.restfulwebservices.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "university_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class University {
	  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String websiteUrl;
    private String nature;
    private String type;
    private String establishmentDate;
    private String priorStatus;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") // Prevent circular serialization
	private List<Contact> contactDetails;//= new ArrayList<>();

    @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") 
    private RecognitionDetails recognitionDetials;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private List<Campus> campuses;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") 
    private List<SraProgramme> sraProgrammes;

    @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private CollegeStats collegeStats;

    private Boolean hasIntegratedProgrammes;
    private Integer totalIntegratedProgrammes;
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private List<EnrollmentDetails> enrollmentDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private List<ChairDetails> chairDetails;

    //Details of UGC Human Resource Development Centre
    private Date hrdcEstablishmentDate=null;
    private Integer hrdcOrientationProgrammesCount=0;
    private Integer hrdcRefresherCourseCount=0;
    private Integer hrdcOwnProgrammesCount=0;
    private Integer hrdctotalProgrammes=0; //last 5 years

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private List<AccreditationDetails> accreditationDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    private List<DepartmentEvaluation> departmentEvaluations;
}
