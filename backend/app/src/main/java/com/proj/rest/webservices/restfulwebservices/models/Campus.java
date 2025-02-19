package com.proj.rest.webservices.restfulwebservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campus {
    @Id
	@GeneratedValue
	private Integer id;

    private String type;
    private String address;
    private String location;
    private String campusArea;
    private String builtUpArea;
    private List<String> programmesOffered;
    private String establishmentDate;
    private String recognitionDate;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    @ToString.Exclude
	private University university;
}
