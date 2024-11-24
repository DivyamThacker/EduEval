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

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") // Prevent circular serialization
    private List<Campus> campuses;//= new ArrayList<>();

    @OneToOne(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("university") // Prevent circular serialization
    private RecognitionDetails recognitionDetials;//= new ArrayList<>();
}


