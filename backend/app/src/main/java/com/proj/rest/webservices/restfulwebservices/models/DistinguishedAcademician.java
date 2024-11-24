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

@Entity(name="distinguished_academician")
@Data
@NoArgsConstructor
public class DistinguishedAcademician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String gender;
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private University university;
}