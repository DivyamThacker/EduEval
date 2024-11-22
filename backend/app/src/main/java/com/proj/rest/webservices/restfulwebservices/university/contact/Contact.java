package com.proj.rest.webservices.restfulwebservices.university.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proj.rest.webservices.restfulwebservices.university.University;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "contact_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
	@GeneratedValue
	private Integer id;

    private String email;
    private String phone;
    private String fax;
    private String designation;
    private String telephone; // with STD Code
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private University university;
}
