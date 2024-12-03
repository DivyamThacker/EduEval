package com.proj.rest.webservices.restfulwebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "accreditation_details")
@Data
@NoArgsConstructor
public class AccreditationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cycleNumber;
    private String type; //accreditationType
    private String grade;
    private Float cgpa;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentDetails peerTeamReport;

    
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private University university;
}
