package com.proj.rest.webservices.restfulwebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "recognition_details")
@Data
@NoArgsConstructor
public class RecognitionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String recognitionDateUnderSection2f;
    private String recognitionDateUnderSection12b;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentDetails recognitionDocument2f;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentDetails recognitionDocument12b;

    private String isUPE;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private University university;
}
