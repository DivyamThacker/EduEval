package com.proj.rest.webservices.restfulwebservices.models;

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
import lombok.ToString;

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
    @ToString.Exclude
	private List<Contact> contactDetails;//= new ArrayList<>();

    @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") 
    @ToString.Exclude
    private RecognitionDetails recognitionDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<Campus> campuses;

    
    @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private CollegeStats collegeStats;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") 
    @ToString.Exclude
    private List<SraProgram> sraPrograms;
    private Boolean areSraProgram=false;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<Faculty> faculties;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<DistinguishedAcademician> distinguishedAcademicians;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<NonTeachingStaff> nonTeachingStaff;

    private Boolean hasIntegratedPrograms;
    private Integer totalIntegratedPrograms;
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<EnrollmentDetails> enrollmentDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<ChairDetails> chairDetails;

    //Details of UGC Human Resource Development Centre
   @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private HrdcDetails hrdcDetails;
    
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<AccreditationDetails> accreditationDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<DepartmentEvaluation> departmentEvaluations;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<NepDetails> nepDetails;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university")
    @ToString.Exclude
    private List<ElectoralLiteracyDetails> electoralLiteracyDetails;
}
