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




@Entity(name = "sra_program")
@Data
@NoArgsConstructor
public class SraProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentDetails sraDocument;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private University university;
}