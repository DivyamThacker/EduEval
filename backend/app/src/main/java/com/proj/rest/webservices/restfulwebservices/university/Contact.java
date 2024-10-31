package com.proj.rest.webservices.restfulwebservices.university;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "contact_details")
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

    protected Contact() {
    }

    public Contact(String email, String phone, String fax, String designation, String telephone, String name) {
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.designation = designation;
        this.telephone = telephone;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
    
    public University getUniversity() {
        return university;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", designation='" + designation + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
