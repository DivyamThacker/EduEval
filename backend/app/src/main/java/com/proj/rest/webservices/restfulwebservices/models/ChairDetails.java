package com.proj.rest.webservices.restfulwebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity(name = "chair_details")
@Data
@NoArgsConstructor
public class ChairDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String departmentName;
    private String chairName;
    private String sponsorName;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private University university;
}
